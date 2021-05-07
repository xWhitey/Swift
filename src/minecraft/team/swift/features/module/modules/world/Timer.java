package team.swift.features.module.modules.world;

import org.lwjgl.input.Keyboard;
import team.swift.event.EventTarget;
import team.swift.event.events.impl.UpdateEvent;
import team.swift.features.module.Module;
import team.swift.features.module.ModuleCategory;
import team.swift.hooks.TimerHook;
import team.swift.settings.Setting;
import team.swift.utils.MovementUtils;

public class Timer extends Module {
    public Setting<Boolean> onMove = new Setting<Boolean>("OnMove", "OnMove", this, false);
    public Setting<Double> speed = new Setting<Double>("Speed", "Speed", this, 1.2, 0.1, 5.0, false);

    public Timer() {
        super("Timer", ModuleCategory.WORLD, Keyboard.KEY_NONE);
    }

    @Override
    public void onDisable() {
        TimerHook.setTimerSpeed(1.0f);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if (onMove.getValBoolean()) {
            if (MovementUtils.isMoving() || mc.player.movementInput.jump) {
                TimerHook.setTimerSpeed(speed.getValDouble());
            } else {
                TimerHook.setTimerSpeed(1.0f);
            }
        } else {
            TimerHook.setTimerSpeed(speed.getValDouble());
        }
    }
}
