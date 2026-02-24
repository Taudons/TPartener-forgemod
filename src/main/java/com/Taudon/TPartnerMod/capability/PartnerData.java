package com.Taudon.TPartnerMod.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class PartnerData implements IPartnerData, ICapabilityProvider
{
    @CapabilityInject(IPartnerData.class)
    public static final Capability<IPartnerData> CAPABILITY = null;

    private int affinity = 0;
    private int mood = 100;
    private boolean spawned = false;
    private String partnerUUID = "";
    
    private final LazyOptional<IPartnerData> lazyOptional = LazyOptional.of(() -> this);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
    {
        return CAPABILITY.orEmpty(cap, lazyOptional);
    }

    @Override
    public int getAffinity()
    {
        return this.affinity;
    }

    @Override
    public void setAffinity(int affinity)
    {
        this.affinity = affinity;
    }

    @Override
    public int getMood()
    {
        return this.mood;
    }

    @Override
    public void setMood(int mood)
    {
        this.mood = mood;
    }

    @Override
    public boolean isSpawned()
    {
        return this.spawned;
    }

    @Override
    public void setSpawned(boolean spawned)
    {
        this.spawned = spawned;
    }

    @Override
    public String getPartnerUUID()
    {
        return this.partnerUUID;
    }

    @Override
    public void setPartnerUUID(String uuid)
    {
        this.partnerUUID = uuid;
    }

    public CompoundNBT serializeNBT()
    {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("affinity", this.affinity);
        nbt.putInt("mood", this.mood);
        nbt.putBoolean("spawned", this.spawned);
        nbt.putString("partnerUUID", this.partnerUUID);
        return nbt;
    }

    public void deserializeNBT(CompoundNBT nbt)
    {
        if (nbt != null)
        {
            this.affinity = nbt.getInt("affinity");
            this.mood = nbt.getInt("mood");
            this.spawned = nbt.getBoolean("spawned");
            this.partnerUUID = nbt.getString("partnerUUID");
        }
    }
}
