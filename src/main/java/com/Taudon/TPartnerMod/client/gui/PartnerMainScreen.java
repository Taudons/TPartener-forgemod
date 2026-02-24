package com.Taudon.TPartnerMod.client.gui;

import com.Taudon.TPartnerMod.TPartnerMod;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class PartnerMainScreen extends Screen
{
    private final PlayerEntity player;
    private final int affinity;
    private final int mood;

    private static final ResourceLocation BACKGROUND = new ResourceLocation(TPartnerMod.MOD_ID, "textures/gui/partner_gui.png");

    public PartnerMainScreen(PlayerEntity player)
    {
        super(new StringTextComponent("Partner GUI"));
        this.player = player;
        this.affinity = 0;
        this.mood = 100;
    }

    @Override
    protected void init()
    {
        super.init();
        // Close button
        this.addButton(new Button(this.width / 2 - 100, this.height / 2 + 50, 200, 20, new StringTextComponent("Close"), (p_214132_1_) -> {
            this.minecraft.setScreen(null);
        }));

        // Greet button
        this.addButton(new Button(this.width / 2 - 100, this.height / 2, 200, 20, new StringTextComponent("Greet (Affinity+1)"), (p_214132_1_) -> {
            this.minecraft.setScreen(null);
        }));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(matrixStack);
        // Draw title
        this.drawCenteredString(matrixStack, this.font, "Partner Info", this.width / 2, this.height / 2 - 40, 0xFFFFFF);

        // Draw data
        this.drawCenteredString(matrixStack, this.font, "Affinity: " + this.affinity, this.width / 2, this.height / 2 - 20, 0xFFFFFF);
        this.drawCenteredString(matrixStack, this.font, "Mood: " + this.mood, this.width / 2, this.height / 2 - 10, 0xFFFFFF);

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}
