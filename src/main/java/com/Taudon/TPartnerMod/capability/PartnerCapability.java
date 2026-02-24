package com.Taudon.TPartnerMod.capability;

import com.Taudon.TPartnerMod.TPartnerMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PartnerCapability
{
    @CapabilityInject(IPartnerData.class)
    public static final Capability<IPartnerData> PARTNER_CAP = null;

    public static final ResourceLocation ID = new ResourceLocation(TPartnerMod.MOD_ID, "partner_data");
}

