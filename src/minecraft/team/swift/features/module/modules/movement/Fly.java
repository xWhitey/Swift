package team.swift.features.module.modules.movement;

import team.swift.event.EventTarget;
import team.swift.event.events.impl.UpdateEvent;
import team.swift.features.module.Module;
import team.swift.features.module.ModuleCategory;
import org.lwjgl.input.Keyboard;
import team.swift.utils.MovementUtils;

/* Created by xWhitey on 05.05.2021 */
public class Fly extends Module {
    public Fly() {
        super("Fly", ModuleCategory.MOVEMENT, Keyboard.KEY_F);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        mc.player.motionY = -0.1D;

        if (Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode())) {
            mc.player.motionY = 2.0;
        }

        if (mc.gameSettings.keyBindSneak.isKeyDown()) {
            mc.player.motionY = -2.0;
        }

        MovementUtils.setSpeed(4.0);
    }
}
