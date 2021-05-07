package team.swift.features.module.modules.render;

import team.swift.event.EventTarget;
import team.swift.event.events.impl.UpdateEvent;
import team.swift.features.module.Module;
import team.swift.features.module.ModuleCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;

/* Created by xWhitey on 05.05.2021 */
public class PlayerESP extends Module {
    public PlayerESP() {
        super("PlayerESP", ModuleCategory.RENDER, Keyboard.KEY_Y);
    }

    @Override
    public void onDisable() {
        for (Entity e : mc.world.loadedEntityList) {
            if (e instanceof EntityPlayer && e.isGlowing()) {
                e.setGlowing(false);
            }
        }
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        for (Entity e : mc.world.loadedEntityList) {
            if (e instanceof EntityPlayer && !e.isGlowing()) {
                e.setGlowing(true);
            }
        }
    }
}
