package com.Taudon.TPartnerMod.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class PartnerCapabilityStorage implements IStorage<IPartnerData>
{
    @CapabilityInject(IPartnerData.class)
    public static final Capability<IPartnerData> CAPABILITY = null;

    @Override
    public CompoundNBT writeNBT(Capability<IPartnerData> capability, IPartnerData instance, Direction side)
    {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<IPartnerData> capability, IPartnerData instance, Direction side, INBT nbt)
    {
        instance.deserializeNBT((CompoundNBT) nbt);
    }
}
