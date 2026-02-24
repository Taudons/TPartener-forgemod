package com.Taudon.TPartnerMod.network;

import com.Taudon.TPartnerMod.TPartnerMod;
import com.Taudon.TPartnerMod.client.gui.PartnerMainScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenPartnerGuiPacket
{
    public OpenPartnerGuiPacket()
    {
    }

    public OpenPartnerGuiPacket(PacketBuffer buf)
    {
    }

    public void toBytes(PacketBuffer buf)
    {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null && mc.screen == null)
            {
                mc.setScreen(new PartnerMainScreen(mc.player));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
