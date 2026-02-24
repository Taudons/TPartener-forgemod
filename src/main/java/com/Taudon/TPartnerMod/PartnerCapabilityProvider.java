package com.taudon.tpartnermod;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

/**
 * Capability Provider，用于将伙伴数据附加到实体上
 */
public class PartnerCapabilityProvider implements ICapabilityProvider {

    private final IPartnerData instance = new PartnerData();

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        // 对于任何请求，都返回伙伴数据的实例
        // Capability 系统会自动处理类型检查
        return LazyOptional.of(() -> (T) instance);
    }
}
