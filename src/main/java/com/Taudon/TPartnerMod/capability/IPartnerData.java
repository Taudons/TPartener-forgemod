package com.Taudon.TPartnerMod.capability;

import net.minecraft.nbt.CompoundNBT;

public interface IPartnerData
{
    int getAffinity();
    void setAffinity(int affinity);
    int getMood();
    void setMood(int mood);
    boolean isSpawned();
    void setSpawned(boolean spawned);
    String getPartnerUUID();
    void setPartnerUUID(String uuid);
    CompoundNBT serializeNBT();
    void deserializeNBT(CompoundNBT nbt);
}
