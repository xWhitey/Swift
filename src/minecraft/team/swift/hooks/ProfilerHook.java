package team.swift.hooks;

import team.swift.event.EventManager;
import team.swift.event.events.impl.Render2DEvent;
import team.swift.event.events.impl.Render3DEvent;
import team.swift.features.module.Module;
import team.swift.features.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.profiler.Profiler;

public class ProfilerHook extends Profiler {
    public static Minecraft mc = Minecraft.getMinecraft();

    public ProfilerHook() {
    }

    public void startSection(String name) {
        if (name.equalsIgnoreCase("gui")) {
            mc.entityRenderer.setupOverlayRendering();

            for (Module module : ModuleManager.modules) {
                if (module.getState()) {
                    EventManager.call(new Render2DEvent());
                }
            }
        }
        if (name.equalsIgnoreCase("render")) {
            for (Module module : ModuleManager.modules) {
                if (module.getState()) {
                    EventManager.call(new Render3DEvent(mc.getRenderPartialTicks()));
                }
            }
        }

        super.startSection(name);
    }
}
