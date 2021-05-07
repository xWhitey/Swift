package team.swift.features.module.modules.render;

import team.swift.event.EventTarget;
import team.swift.event.events.impl.Render2DEvent;
import team.swift.event.events.impl.UpdateEvent;
import team.swift.features.module.Module;
import team.swift.features.module.ModuleCategory;
import team.swift.features.module.ModuleManager;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.Comparator;

/* Created by xWhitey on 05.05.2021 */
public class HUD extends Module {
    public HUD() {
        super("HUD", ModuleCategory.RENDER, Keyboard.KEY_H);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {

    }

    @EventTarget
    public void onRender2D(Render2DEvent event) {
        drawArrayList();
    }

    private void drawArrayList() {
        ScaledResolution sr = new ScaledResolution(mc);
        int scaledWidth = sr.getScaledWidth();
        final float[] pos = {-0.9F};
        final int[] counter = {1};

        ModuleManager.modules.stream().filter(Module::getState).sorted(Comparator.comparingInt(function -> -mc.fontRendererObj.getStringWidth(function.getName()))).
                forEach(function -> {
                    float yPos = 12.0F * pos[0];
                    String functionName = function.getName();
                    int stringWidth = mc.fontRendererObj.getStringWidth(functionName);
                    double offset = function.offset();

                    if (!function.getState()) {
                        offset = 1.0D - offset;
                    }

                    if (offset != 0.0D) {
                        long idk = Math.round(stringWidth * Math.sin(Math.toRadians(offset * 90.0D)));
                        int l = 6;
                        int i1 = 0;
                        mc.fontRendererObj.drawStringWithShadow(functionName, scaledWidth - idk + 3.0F - l - i1, 13.5F + yPos,
                                rainbow(400000000L * counter[0]).getRGB());
                        pos[0] += 0.8F;
                        counter[0]++;
                    }
                });
    }

    public Color rainbow(long offset) {
        Color currentColor = new Color(Color.HSBtoRGB((System.nanoTime() + offset) / 10000000000F % 1, 1F, 1F));
        return new Color(currentColor.getRed() / 255F, currentColor.getGreen() / 255F, currentColor.getBlue() / 255F,
                currentColor.getAlpha() / 255F);
    }
}
