package com.Taudon.TPartnerMod.item;

import com.Taudon.TPartnerMod.TPartnerMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SummonItem extends Item
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String PARTNER_TAG = "tpartner_uuid";

    public SummonItem()
    {
        super(new Item.Properties());
    }

    // Static method to handle the actual summon logic
    public static void trySummon(PlayerEntity player, ItemStack stack)
    {
        LOGGER.info("trySummon called");
        
        // Check if player is valid
        if (player == null)
        {
            LOGGER.error("Player is NULL!");
            return;
        }
        
        // Check if player level is valid
        if (player.level == null)
        {
            LOGGER.error("Player level is NULL!");
            return;
        }

        LOGGER.info("Player level is valid, checking if ServerWorld");
        
        // Only create entity on server side - check if it's a ServerWorld
        if (player.level instanceof ServerWorld)
        {
            LOGGER.info("Creating Partner on server");

            // Check if partner already exists
            String playerUUID = player.getUUID().toString();
            LOGGER.info("Checking for existing partner for player: " + playerUUID);
            
            // Find existing partner
            ServerWorld serverWorld = (ServerWorld) player.level;
            ArmorStandEntity existingPartner = findPartner(serverWorld, playerUUID);
            
            if (existingPartner != null)
            {
                LOGGER.info("Existing partner found! Teleporting to player");
                // Teleport to player
                Vector3d playerPos = player.position();
                existingPartner.setPos(playerPos.x, playerPos.y, playerPos.z);
                
                // Send message to player
                player.sendMessage(new net.minecraft.util.text.StringTextComponent("Partner teleported to you!"), player.getUUID());
                
                // Don't consume item anymore
                return;
            }

            LOGGER.info("No existing partner found, creating new one");

            try
            {
                // Spawn ArmorStand using vanilla type
                ArmorStandEntity armorStand = new ArmorStandEntity(
                    EntityType.ARMOR_STAND, 
                    player.level
                );
                
                LOGGER.info("Entity created: " + armorStand);
                
                // Get player position
                Vector3d playerPos = player.position();
                double x = playerPos.x;
                double y = playerPos.y;
                double z = playerPos.z;
                
                LOGGER.info("Setting position: " + x + ", " + y + ", " + z);
                armorStand.setPos(x, y, z);
                
                // Disable gravity so it floats
                armorStand.setNoGravity(true);
                
                // Make it small (optional, looks more like a fairy)
                // Note: setSmall is private in ArmorStandEntity, we can't call it directly
                
                // Add custom NBT tag to identify this as our partner
                CompoundNBT nbt = armorStand.getPersistentData();
                nbt.putString(PARTNER_TAG, playerUUID);
                LOGGER.info("Added partner tag: " + playerUUID);

                LOGGER.info("Adding entity to world");
                boolean added = player.level.addFreshEntity(armorStand);
                LOGGER.info("Entity added: " + added);

                if (added)
                {
                    // Send message to player
                    player.sendMessage(new net.minecraft.util.text.StringTextComponent("Partner summoned!"), player.getUUID());
                    
                    // Don't consume item anymore
                    // The item acts as a key to the partner
                }
            }
            catch (Exception e)
            {
                LOGGER.error("Exception during entity creation:", e);
            }
        }
        else
        {
            LOGGER.warn("World is client side, skipping entity creation");
        }
    }

    // Static method to handle despawn logic (Shift + Right Click)
    public static void tryDespawn(PlayerEntity player, ItemStack stack)
    {
        LOGGER.info("tryDespawn called");
        
        if (player == null || player.level == null)
        {
            return;
        }

        if (player.level instanceof ServerWorld)
        {
            String playerUUID = player.getUUID().toString();
            ServerWorld serverWorld = (ServerWorld) player.level;
            ArmorStandEntity partner = findPartner(serverWorld, playerUUID);
            
            if (partner != null)
            {
                LOGGER.info("Partner found, removing...");
                partner.remove();
                
                player.sendMessage(new net.minecraft.util.text.StringTextComponent("Partner removed!"), player.getUUID());
                
                // We don't give back the item because the player is holding it.
                // The item remains in hand.
            }
            else
            {
                player.sendMessage(new net.minecraft.util.text.StringTextComponent("No partner found to remove."), player.getUUID());
            }
        }
    }
    
    // Helper method to find existing partner
    private static ArmorStandEntity findPartner(ServerWorld world, String playerUUID)
    {
        // Get all entities in the world (around the player to improve performance)
        // We'll just search in a large area around the origin (0,0,0) or use a simpler approach
        // Actually, let's iterate over all entities in the world - this is not efficient but works for single player
        
        // Use a larger search area
        net.minecraft.util.math.AxisAlignedBB searchBox = new net.minecraft.util.math.AxisAlignedBB(
            Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE,
            Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE
        );
        
        for (ArmorStandEntity entity : world.getEntitiesOfClass(ArmorStandEntity.class, searchBox, e -> true))
        {
            CompoundNBT nbt = entity.getPersistentData();
            if (nbt.contains(PARTNER_TAG))
            {
                String tagUUID = nbt.getString(PARTNER_TAG);
                LOGGER.info("Found entity with tag: " + tagUUID);
                if (tagUUID.equals(playerUUID))
                {
                    return entity;
                }
            }
        }
        return null;
    }
}
