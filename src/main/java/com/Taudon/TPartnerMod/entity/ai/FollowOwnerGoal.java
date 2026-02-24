package com.Taudon.TPartnerMod.entity.ai;

import com.Taudon.TPartnerMod.entity.TPartnerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.vector.Vector3d;

import java.util.EnumSet;
import java.util.Random;

public class FollowOwnerGoal extends Goal
{
    private final TPartnerEntity entity;
    private PlayerEntity owner;
    private final double speed;
    private final float minDistance;
    private final float maxDistance;

    public FollowOwnerGoal(TPartnerEntity entity, double speed, float minDistance, float maxDistance)
    {
        this.entity = entity;
        this.speed = speed;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse()
    {
        return true;
    }

    public boolean canContinueToUse()
    {
        return true;
    }

    public void startExecuting()
    {
    }

    public void tick()
    {
        // Do nothing
    }
}
