package com.taudon.tpartnermod.client;

import com.taudon.tpartnermod.util.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 客户端初始化
 */
public class ClientSetup {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * 注册自定义渲染器
     */
    public static void init() {
        LOGGER.info("初始化客户端...");

        // 注意：由于当前使用游戏内置的盔甲架作为伙伴实体可视化，
        // 不需要额外注册渲染器，游戏会自动使用 ArmorStandRenderer

        LOGGER.info("客户端初始化完成");
    }

    /**
     * 注册实体渲染器
     * @deprecated 当前版本使用游戏内置实体，不需要自定义渲染器
     */
    @Deprecated
    public static <T extends LivingEntity> void registerEntityRenderer(EntityType<T> entityType, LivingRenderer<T, ?> renderer) {
        Minecraft minecraft = Minecraft.getInstance();
        EntityRendererManager renderManager = minecraft.getRenderManager();
        renderManager.register(entityType, (EntityRenderer) renderer);
    }
}

