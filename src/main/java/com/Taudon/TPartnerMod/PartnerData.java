package com.taudon.tpartnermod;

import net.minecraft.nbt.CompoundNBT;

/**
 * 伙伴数据实现类，实现 IPartnerData 接口
 */
public class PartnerData implements IPartnerData {

    private static final String NBT_NAME = "PartnerName";
    private static final String NBT_LEVEL = "PartnerLevel";
    private static final String NBT_EXPERIENCE = "PartnerExperience";

    private String name;
    private int level;
    private int experience;

    public PartnerData() {
        this.name = "Partner";
        this.level = 1;
        this.experience = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int getExperience() {
        return experience;
    }

    @Override
    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString(NBT_NAME, this.name);
        nbt.putInt(NBT_LEVEL, this.level);
        nbt.putInt(NBT_EXPERIENCE, this.experience);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (nbt.contains(NBT_NAME)) {
            this.name = nbt.getString(NBT_NAME);
        }
        if (nbt.contains(NBT_LEVEL)) {
            this.level = nbt.getInt(NBT_LEVEL);
        }
        if (nbt.contains(NBT_EXPERIENCE)) {
            this.experience = nbt.getInt(NBT_EXPERIENCE);
        }
    }
}

