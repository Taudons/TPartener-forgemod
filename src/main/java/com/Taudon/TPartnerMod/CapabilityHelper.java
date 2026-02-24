package com.taudon.tpartnermod;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * Capability 辅助类，用于存储 Capability 实例
 */
public class CapabilityHelper {

    private CapabilityHelper() {}

    /**
     * Capability 存储实现
     */
    public static final IStorage<IPartnerData> PARTNER_DATA_STORAGE = new IStorage<IPartnerData>() {
        @Override
        public INBT writeNBT(Capability<IPartnerData> capability, IPartnerData instance, Direction side) {
            return instance.serializeNBT();
        }

        @Override
        public void readNBT(Capability<IPartnerData> capability, IPartnerData instance, Direction side, INBT nbt) {
            if (nbt instanceof CompoundNBT) {
                instance.deserializeNBT((CompoundNBT) nbt);
            }
        }
    };

    /**
     * 注册 Capability
     */
    public static void registerCapability() {
        CapabilityManager.INSTANCE.register(IPartnerData.class, PARTNER_DATA_STORAGE, PartnerData::new);
    }
}
