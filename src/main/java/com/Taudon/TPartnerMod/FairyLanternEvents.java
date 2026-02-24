package com.taudon.tpartnermod;

import com.taudon.tpartnermod.item.FairyLanternItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 处理精灵提灯的右键交互事件 - MCP 版本
 */
public class FairyLanternEvents {

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        LOGGER.info("[FairyLanternEvents] onRightClickItem called!");
        
        PlayerEntity player = event.getPlayer();
        ItemStack stack = event.getItemStack();
        
        // 检查是否是精灵提灯
        if (!(stack.getItem() instanceof FairyLanternItem)) {
            LOGGER.info("[FairyLanternEvents] Not a FairyLantern item");
            return;
        }
        
        LOGGER.info("[FairyLanternEvents] This is a FairyLantern!");

        // 使用 getWorld 获取 ServerWorld
        if (!(event.getWorld() instanceof ServerWorld)) {
            LOGGER.info("[FairyLanternEvents] Not a server world, skipping");
            return;
        }
        
        ServerWorld world = (ServerWorld) event.getWorld();
        LOGGER.info("[FairyLanternEvents] Running on server");

        // 获取玩家位置
        double x = player.getPosX();
        double y = player.getPosY();
        double z = player.getPosZ();
        
        LOGGER.info("[FairyLanternEvents] Player position: " + x + ", " + y + ", " + z);
        
        // 在玩家位置上方生成实体
        double spawnX = x;
        double spawnY = y + 1.0;
        double spawnZ = z;

        LOGGER.info("[FairyLanternEvents] Spawn position: " + spawnX + ", " + spawnY + ", " + spawnZ);

        // 创建伙伴实体 - 使用 EntityType 创建
        Entity entity = null;
        if (TPartnerEntityType.PARTNER_ENTITY_TYPE != null) {
            entity = TPartnerEntityType.PARTNER_ENTITY_TYPE.create(world);
        } else {
            // 后备：创建猪实体
            entity = EntityType.PIG.create(world);
        }
        LOGGER.info("[FairyLanternEvents] Created entity: " + entity);
        
        if (entity != null) {
            // 设置位置 - 使用 setPositionAndRotation
            entity.setPositionAndRotation(spawnX, spawnY, spawnZ, player.rotationYaw, player.rotationPitch);
            LOGGER.info("[FairyLanternEvents] Set position");
            
            // 添加到世界
            world.addEntity(entity);
            LOGGER.info("[FairyLanternEvents] Added entity to world");
            
            // 消耗物品
            stack.shrink(1);
            LOGGER.info("[FairyLanternEvents] Shrunk item, now has " + stack.getCount());
        } else {
            LOGGER.error("[FairyLanternEvents] Entity is null!");
        }
    }
}
