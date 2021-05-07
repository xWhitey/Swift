package team.swift.features.module.modules.combat;

import team.swift.event.EventTarget;
import team.swift.event.events.impl.UpdateEvent;
import team.swift.features.module.Module;
import team.swift.features.module.ModuleCategory;
import team.swift.utils.RotationUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;

/* Created by katch on 05.05.2021 */
public class Aim extends Module {
    public Aim() {
        super("Aim", ModuleCategory.COMBAT, Keyboard.KEY_NONE);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        for (Entity e : mc.world.loadedEntityList) {
            if (e instanceof EntityPlayer) {
                float[] rotations = RotationUtils.getRotation(e);
                mc.player.rotationYaw = rotations[0];
                mc.player.rotationPitch = rotations[1];
            }
        }
    }
}
