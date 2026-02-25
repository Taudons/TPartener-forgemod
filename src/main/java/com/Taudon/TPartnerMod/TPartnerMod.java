package com.taudon.tpartnermod;

import com.taudon.tpartnermod.client.ClientSetup;
import com.taudon.tpartnermod.event.CapabilityEvents;
import com.taudon.tpartnermod.event.FairyLanternEvents;
import com.taudon.tpartnermod.item.FairyLanternItem;
import com.taudon.tpartnermod.util.Constants;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TPartner 模组主类
 * 玩家可以通过精灵提灯生成伙伴实体
 */
@Mod(Constants.MOD_ID)
public class TPartnerMod {

    // 日志记录器
    public static final Logger LOGGER = LogManager.getLogger();

    public TPartnerMod() {
        // 注册通用设置事件
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // 注册客户端设置事件
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // 注册游戏事件
        MinecraftForge.EVENT_BUS.register(this);
        // 注册 Capability 事件
        MinecraftForge.EVENT_BUS.register(new CapabilityEvents());
        // 注册精灵提灯交互事件
        MinecraftForge.EVENT_BUS.register(new FairyLanternEvents());
    }

    /**
     * 通用设置 - 在游戏初始化时调用
     */
    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("=== {} 初始化开始 ===", Constants.MOD_NAME);
        LOGGER.info("=== {} 初始化完成 ===", Constants.MOD_NAME);
    }

    /**
     * 客户端设置 - 在客户端初始化时调用
     */
    private void doClientStuff(final FMLClientSetupEvent event) {
        LOGGER.info("初始化客户端...");
        ClientSetup.init();
    }

    /**
     * 服务器启动事件
     */
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("服务器启动中...");
    }

    /**
     * 方块注册事件
     */
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            LOGGER.info("注册方块...");
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            LOGGER.info("注册物品...");

            // 注册精灵提灯
            event.getRegistry().register(new FairyLanternItem());
            LOGGER.info("物品注册完成: {}", Constants.ITEM_FAIRY_LANTERN_ID);
        }
    }
}
