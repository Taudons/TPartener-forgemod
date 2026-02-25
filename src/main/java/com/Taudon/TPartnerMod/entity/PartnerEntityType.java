package com.taudon.tpartnermod.entity;

import com.taudon.tpartnermod.util.Constants;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 伙伴实体类型注册
 */
public class PartnerEntityType {

    private static final Logger LOGGER = LogManager.getLogger();

    // 伙伴实体的 EntityType
    public static EntityType<PartnerEntity> PARTNER_ENTITY_TYPE;

    /**
     * 创建并注册实体类型
     */
    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registry {

        @SubscribeEvent
        public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event) {
            LOGGER.info("注册伙伴实体类型...");

            // 创建实体类型
            PARTNER_ENTITY_TYPE = (EntityType<PartnerEntity>) EntityType.Builder.create(
                new EntityType.IFactory<PartnerEntity>() {
                    @Override
                    public PartnerEntity create(EntityType<PartnerEntity> type, World world) {
                        return new PartnerEntity(type, world);
                    }
                },
                EntityClassification.CREATURE
            ).build(Constants.ENTITY_PARTNER_ID);

            // 设置注册名
            PARTNER_ENTITY_TYPE.setRegistryName(Constants.MOD_ID, Constants.ENTITY_PARTNER_ID);

            LOGGER.info("创建伙伴实体类型: {}", PARTNER_ENTITY_TYPE.getRegistryName());

            // 注册
            event.getRegistry().register(PARTNER_ENTITY_TYPE);
            LOGGER.info("实体类型注册完成");
        }
    }
}

