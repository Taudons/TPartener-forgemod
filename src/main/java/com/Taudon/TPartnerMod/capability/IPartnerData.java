package com.taudon.tpartnermod.capability;

import net.minecraft.nbt.CompoundNBT;

/**
 * 伙伴数据接口 - Capability 接口
 */
public interface IPartnerData {

    /**
     * 获取伙伴名称
     */
    String getName();

    /**
     * 设置伙伴名称
     */
    void setName(String name);

    /**
     * 获取伙伴等级
     */
    int getLevel();

    /**
     * 设置伙伴等级
     */
    void setLevel(int level);

    /**
     * 获取伙伴经验值
     */
    int getExperience();

    /**
     * 设置伙伴经验值
     */
    void setExperience(int experience);

    /**
     * 获取伙伴 UUID
     */
    String getPartnerUuid();

    /**
     * 设置伙伴 UUID
     */
    void setPartnerUuid(String uuid);

    /**
     * 序列化数据到 NBT
     */
    CompoundNBT serializeNBT();

    /**
     * 从 NBT 反序列化数据
     */
    void deserializeNBT(CompoundNBT nbt);
}

