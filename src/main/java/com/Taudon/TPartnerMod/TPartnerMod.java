package com.Taudon.TPartnerMod;

import com.Taudon.TPartnerMod.capability.PartnerData;
import com.Taudon.TPartnerMod.entity.TPartnerEntity;
import com.Taudon.TPartnerMod.item.SummonItem;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
// import net.minecraftforge.fml.common.network.NetworkRegistry;
// import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TPartnerMod.MOD_ID)
public class TPartnerMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID="tpartnermod";

    // 实体类型声明 - 使用 ArmorStandEntity 类型
    public static EntityType<TPartnerEntity> PARTNER_ENTITY_TYPE;
    public static Item SUMMON_ITEM;
    // public static final String CHANNEL_NAME = "tpartner_channel";
    // public static SimpleNetworkWrapper INSTANCE;

    public TPartnerMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // TODO: 网络包注册暂时禁用，等确认正确的导入路径后再启用
        // INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, CHANNEL_NAME), () -> "1", s -> true, s -> true);
        // INSTANCE.registerMessage(0, OpenPartnerGuiPacket.class, OpenPartnerGuiPacket::toBytes, OpenPartnerGuiPacket::new, OpenPartnerGuiPacket::handle);

        LOGGER.info("TPartner Capability Initialized");
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    @SuppressWarnings("unchecked")
    private void doClientStuff(final FMLClientSetupEvent event) {
        // Entity renderer should be automatically handled since we extend ArmorStandEntity
        // which already has a default renderer
        LOGGER.info("TPartnerEntity extends ArmorStandEntity - using default renderer");
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // Listen for player login event to attach Capability
    @SubscribeEvent
    public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) event.getObject();
            // Check if already attached to avoid duplication
            if (!player.getCapability(PartnerData.CAPABILITY, null).isPresent())
            {
                event.addCapability(new ResourceLocation(MOD_ID, "partner_data"), new PartnerData());
            }
        }
    }

    // Listen for player right-click event to handle summon item
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickItem event)
    {
        PlayerEntity player = event.getPlayer();
        if (player != null && player.getItemInHand(event.getHand()).getItem() == SUMMON_ITEM)
        {
            LOGGER.info("Player used Summon Item!");
            
            if (player.isShiftKeyDown())
            {
                LOGGER.info("Player is holding shift, trying to remove partner");
                SummonItem.tryDespawn(player, player.getItemInHand(event.getHand()));
            }
            else
            {
                LOGGER.info("Player is not holding shift, trying to summon partner");
                SummonItem.trySummon(player, player.getItemInHand(event.getHand()));
            }
            
            event.setCanceled(true);
        }
    }

    // Listen for player right-click entity event
    @SubscribeEvent
    public void onPlayerInteractEntity(PlayerInteractEvent.EntityInteract event)
    {
        LOGGER.info("onPlayerInteractEntity called!");
        
        PlayerEntity player = event.getPlayer();
        if (player != null)
        {
            LOGGER.info("Player is not null, target is: " + event.getTarget());
            
            if (event.getTarget() instanceof net.minecraft.entity.item.ArmorStandEntity)
            {
                net.minecraft.entity.item.ArmorStandEntity armorStand = (net.minecraft.entity.item.ArmorStandEntity) event.getTarget();
                
                // Check if it's our partner
                if (armorStand.getPersistentData().contains(SummonItem.PARTNER_TAG))
                {
                    LOGGER.info("Target is our Partner!");
                    
                    if (player.isShiftKeyDown())
                    {
                        LOGGER.info("Player is holding shift, removing entity");
                        if (!player.level.isClientSide)
                        {
                            event.getTarget().remove();
                            LOGGER.info("Partner removed");
                            
                            // Send message
                            player.sendMessage(new net.minecraft.util.text.StringTextComponent("Partner removed!"), player.getUUID());
                        }
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event)
        {
            // 注册物品
            SUMMON_ITEM = new SummonItem();
            SUMMON_ITEM.setRegistryName(new ResourceLocation(MOD_ID, "summon_item"));
            event.getRegistry().register(SUMMON_ITEM);
            LOGGER.info("Summon Item Registered: " + SUMMON_ITEM.getRegistryName());
        }

        @SuppressWarnings("unchecked")
        @SubscribeEvent
        public static void onEntityTypeRegistry(final RegistryEvent.Register<EntityType<?>> event)
        {
            // Use EntityType.ARMOR_STAND as a template and cast
            // This allows us to use the existing ArmorStand renderer
            EntityType<TPartnerEntity> type = (EntityType<TPartnerEntity>) (EntityType<?>) net.minecraft.entity.EntityType.Builder.of(
                (entityType, world) -> {
                    // Create a new TPartnerEntity but pass the ArmorStand type
                    return new TPartnerEntity((net.minecraft.entity.EntityType<ArmorStandEntity>) (EntityType<?>) entityType, world);
                },
                net.minecraft.entity.EntityClassification.MISC
            ).build("tpartner");
            
            PARTNER_ENTITY_TYPE = type;
            PARTNER_ENTITY_TYPE.setRegistryName(new ResourceLocation(MOD_ID, "tpartner"));
            event.getRegistry().register(PARTNER_ENTITY_TYPE);
            LOGGER.info("TPartner Entity Registered: " + PARTNER_ENTITY_TYPE.getRegistryName());
        }
    }
}
