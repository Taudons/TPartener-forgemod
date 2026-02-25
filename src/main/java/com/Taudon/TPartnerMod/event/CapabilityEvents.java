package com.taudon.tpartnermod.event;

import com.taudon.tpartnermod.util.Constants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Capability 事件处理 - 将伙伴数据附加到实体上
 * 注意：Capability 功能暂未完全实现
 */
@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class CapabilityEvents {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        // TODO: 实现伙伴数据 Capability
        // 当前仅记录日志
    }
}
