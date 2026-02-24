package com.taudon.tpartnermod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * 伙伴实体 - 玩家可以通过精灵提灯生成的伙伴
 */
public class TPartnerEntity extends net.minecraft.entity.Entity {

    // 实体类型 ID
    public static final String ENTITY_ID = "partner";
    public static final ResourceLocation ENTITY_KEY = new ResourceLocation("tpartnermod", ENTITY_ID);

    public TPartnerEntity(World world) {
        super(EntityType.PIG, world); // 临时使用 PIG
    }

    // 1.16.5 SRG 方法
    protected void defineSynchedData() {
        // 注册实体同步数据
    }

    public void registerData() {
        // 注册实体数据参数
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        // 从 NBT 读取数据
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        // 写入数据到 NBT
    }

    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void tick() {
        super.tick();
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean canBeLeashedTo(PlayerEntity player) {
        return true;
    }

    /**
     * 创建伙伴实体实例
     */
    public static TPartnerEntity create(World world) {
        return new TPartnerEntity(world);
    }
}
