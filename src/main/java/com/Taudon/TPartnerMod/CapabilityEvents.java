package com.taudon.tpartnermod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Capability 事件处理类，用于将伙伴数据附加到实体上
 */
@Mod.EventBusSubscriber(modid = TPartnerMod.MOD_ID)
public class CapabilityEvents {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();

        // 为玩家附加伙伴数据 capability
        if (entity instanceof PlayerEntity) {
            event.addCapability(
                new ResourceLocation(TPartnerMod.MOD_ID, "partner_data"),
                new PartnerCapabilityProvider()
            );
        }

        // 为伙伴实体附加伙伴数据 capability
        if (entity instanceof TPartnerEntity) {
            event.addCapability(
                new ResourceLocation(TPartnerMod.MOD_ID, "partner_data"),
                new PartnerCapabilityProvider()
            );
        }
    }
}
