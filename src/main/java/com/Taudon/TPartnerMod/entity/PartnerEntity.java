package com.taudon.tpartnermod.entity;

import com.taudon.tpartnermod.util.Constants;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * 伙伴实体 - 玩家通过精灵提灯生成的伙伴
 * 注意：当前使用游戏内置的盔甲架作为可视化，实际伙伴数据通过 Capability 存储
 */
public class PartnerEntity extends MobEntity {

    public static final ResourceLocation ENTITY_KEY = new ResourceLocation(Constants.MOD_ID, Constants.ENTITY_PARTNER_ID);

    public PartnerEntity(EntityType<? extends MobEntity> type, World world) {
        super(type, world);
        initAttributes();
    }

    private void initAttributes() {
        // 确保属性已初始化
        if (this.getAttribute(Attributes.MAX_HEALTH) != null) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
        }
    }

    @Override
    protected void registerData() {
        // 注册实体数据同步
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
    }

    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    /**
     * 检查实体是否可见
     */
    public boolean isInvisible() {
        return false;
    }
}

