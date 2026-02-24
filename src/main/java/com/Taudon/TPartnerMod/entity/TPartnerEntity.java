package com.Taudon.TPartnerMod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.UUID;

public class TPartnerEntity extends ArmorStandEntity
{
    private UUID ownerUUID;

    public TPartnerEntity(EntityType<? extends ArmorStandEntity> type, World world)
    {
        super(type, world);
    }

    @Override
    public IPacket<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void setOwnerUUID(UUID uuid)
    {
        this.ownerUUID = uuid;
    }

    public UUID getOwnerUUID()
    {
        return this.ownerUUID;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound)
    {
        if (this.ownerUUID != null)
        {
            compound.putUUID("OwnerUUID", this.ownerUUID);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound)
    {
        if (compound.hasUUID("OwnerUUID"))
        {
            this.ownerUUID = compound.getUUID("OwnerUUID");
        }
    }

    @Override
    public ActionResultType interact(PlayerEntity player, Hand hand)
    {
        if (player.isShiftKeyDown())
        {
            if (!this.level.isClientSide)
            {
                this.remove();
            }
            return ActionResultType.SUCCESS;
        }
        return super.interact(player, hand);
    }
}
