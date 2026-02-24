package com.taudon.tpartnermod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 伙伴实体类型注册
 */
@Mod.EventBusSubscriber(modid = TPartnerMod.MOD_ID)
public class TPartnerEntityType {

    // 伙伴实体的 EntityType
    public static EntityType<TPartnerEntity> PARTNER_ENTITY_TYPE;

    /**
     * 注册实体类型
     */
    @SubscribeEvent
    public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event) {
        // 创建简单的实体类型
        // 在 1.16.5 SRG 中，使用 EntityType.Builder.create() 的重载版本
        // 或者直接使用 EntityType.Builder.of() 方法
        
        // 方法1: 使用 EntityType.Builder.of() - 简单版本
        EntityType<TPartnerEntity> entityType = EntityType.Builder.of(
            new EntityType.IFactory<TPartnerEntity>() {
                @Override
                public TPartnerEntity create(EntityType<TPartnerEntity> type, World world) {
                    return new TPartnerEntity(world);
                }
            },
            EntityClassification.CREATURE
        ).build("partner");

        // 设置 registry name
        entityType.setRegistryName(TPartnerMod.MOD_ID, "partner");
        
        // 保存到静态字段
        PARTNER_ENTITY_TYPE = entityType;
        
        // 注册到事件
        event.getRegistry().register(entityType);
    }
}
