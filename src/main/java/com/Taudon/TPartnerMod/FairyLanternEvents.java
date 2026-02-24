package com.taudon.tpartnermod;

import com.taudon.tpartnermod.item.FairyLanternItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.lang.reflect.Field;

/**
 * 处理精灵提灯的右键交互事件
 * 使用反射访问 1.16.5 SRG 映射的私有字段
 */
public class FairyLanternEvents {

    @SubscribeEvent
    public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        PlayerEntity player = event.getPlayer();
        ItemStack stack = event.getItemStack();
        
        // 检查是否是精灵提灯
        if (!isFairyLantern(stack)) {
            return;
        }

        World world = event.getWorld();
        
        // 只在服务器端执行
        if (isClientWorld(world)) {
            return;
        }

        // 尝试生成伙伴实体
        boolean spawned = spawnPartner(world, player);
        
        if (spawned) {
            // 消耗一个物品
            stack.shrink(1);
            event.setCanceled(true);
        }
    }

    /**
     * 检查是否是精灵提灯
     */
    private boolean isFairyLantern(ItemStack stack) {
        return stack.getItem() instanceof FairyLanternItem;
    }

    /**
     * 检查是否是客户端世界 (使用反射访问 field_72995_K)
     */
    private boolean isClientWorld(World world) {
        try {
            Field isRemoteField = World.class.getDeclaredField("field_72995_K");
            isRemoteField.setAccessible(true);
            return isRemoteField.getBoolean(world);
        } catch (Exception e) {
            // 如果反射失败，尝试其他方法
            return false;
        }
    }

    /**
     * 生成伙伴实体 - 使用反射访问私有字段
     */
    private boolean spawnPartner(World world, PlayerEntity player) {
        // 创建伙伴实体
        TPartnerEntity entity = new TPartnerEntity(world);

        // 使用反射获取玩家坐标
        double x, y, z;
        try {
            // 1.16.5 SRG 字段名
            Field posX = PlayerEntity.class.getDeclaredField("field_70165_t");
            Field posY = PlayerEntity.class.getDeclaredField("field_70163_u");
            Field posZ = PlayerEntity.class.getDeclaredField("field_70161_s");
            
            posX.setAccessible(true);
            posY.setAccessible(true);
            posZ.setAccessible(true);
            
            x = posX.getDouble(player) + 0.5;
            y = posY.getDouble(player) + 1;
            z = posZ.getDouble(player) + 0.5;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        // 使用反射设置实体位置
        try {
            // Entity.setPosition(double, double, double)
            // 在 SRG 中可能是 setPosition 或者其他方法
            Entity.class.getMethod("setPosition", double.class, double.class, double.class)
                .invoke(entity, x, y, z);
        } catch (Exception e) {
            // 尝试直接设置字段
            try {
                Field posXField = Entity.class.getDeclaredField("field_70165_t");
                Field posYField = Entity.class.getDeclaredField("field_70163_u");
                Field posZField = Entity.class.getDeclaredField("field_70161_s");
                posXField.setAccessible(true);
                posYField.setAccessible(true);
                posZField.setAccessible(true);
                posXField.setDouble(entity, x);
                posYField.setDouble(entity, y);
                posZField.setDouble(entity, z);
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }

        // 使用反射添加到世界
        try {
            // World.addEntity(Entity)
            World.class.getMethod("addEntity", Entity.class).invoke(world, entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
}
