package com.taudon.tpartnermod.capability;

import com.taudon.tpartnermod.util.Constants;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * Capability 辅助类
 */
public class CapabilityHelper {

    private CapabilityHelper() {}

    // Capability Key
    public static final ResourceLocation PARTNER_CAPABILITY_KEY = new ResourceLocation(Constants.MOD_ID, Constants.CAPABILITY_PARTNER_KEY);

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
    public static void register() {
        CapabilityManager.INSTANCE.register(IPartnerData.class, PARTNER_DATA_STORAGE, PartnerData::new);
    }
}
