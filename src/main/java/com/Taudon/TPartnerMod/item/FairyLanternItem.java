package com.taudon.tpartnermod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * 精灵提灯 - 右键使用时生成伙伴实体
 * 实际生成逻辑在 FairyLanternEvents 中处理
 */
public class FairyLanternItem extends Item {

    public FairyLanternItem(Properties properties) {
        super(properties);
    }

    /**
     * 处理右键点击 - 直接返回 PASS，让 FairyLanternEvents 处理
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        // 实际逻辑在 FairyLanternEvents 中处理
        // 这里直接返回 PASS，让事件继续传递
        return super.onItemRightClick(world, player, hand);
    }
}
