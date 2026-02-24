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
        if (!(event.getObject() instanceof PlayerEntity)) {
            return;
        }

        event.addCapability(
                new ResourceLocation(TPartnerMod.MOD_ID, "partner_data"),
                new PartnerCapabilityProvider()
        );
    }
}
