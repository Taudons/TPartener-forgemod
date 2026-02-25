package com.taudon.tpartnermod.event;

import com.taudon.tpartnermod.item.FairyLanternItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 处理精灵提灯的右键交互事件
 */
public class FairyLanternEvents {

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        PlayerEntity player = event.getPlayer();
        ItemStack stack = event.getItemStack();

        // 检查是否是精灵提灯
        if (!(stack.getItem() instanceof FairyLanternItem)) {
            return;
        }

        // 检查是否是服务器世界
        if (!(event.getWorld() instanceof ServerWorld)) {
            return;
        }

        ServerWorld world = (ServerWorld) event.getWorld();

        // 获取生成位置
        double x = player.getPosX();
        double y = player.getPosY() + 1.0;
        double z = player.getPosZ();

        // 创建伙伴实体 (使用盔甲架作为可视化)
        Entity entity = EntityType.ARMOR_STAND.create(world);

        if (entity != null) {
            // 设置位置和旋转
            entity.setPosition(x, y, z);
            entity.rotationYaw = player.rotationYaw;
            entity.rotationPitch = player.rotationPitch;

            // 添加到世界
            world.addEntity(entity);

            // 消耗物品
            stack.shrink(1);

            // 设置事件结果
            event.setResult(Result.ALLOW);

            LOGGER.info("玩家 {} 生成了伙伴实体", player.getName().getString());
        }
    }
}
