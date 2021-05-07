package team.swift.features.module.modules.combat;

import team.swift.event.EventTarget;
import team.swift.event.events.impl.UpdateEvent;
import team.swift.features.module.Module;
import team.swift.features.module.ModuleCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;
import team.swift.utils.MSTimer;
import team.swift.utils.RotationUtils;

/* Created by xWhitey on 05.05.2021 */
public class KillAura extends Module {
    public KillAura() {
        super("KillAura", ModuleCategory.COMBAT, Keyboard.KEY_R);
    }

    private final MSTimer msTimer = new MSTimer();

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        for (Entity e : mc.world.loadedEntityList) {
            if (e instanceof EntityPlayer && e != mc.player && mc.player.getDistanceToEntity(e) < 6.0) {
                float[] rotations = RotationUtils.getRotation(e);
                RotationUtils.setTargetRotation(rotations[0], rotations[1]);
                mc.player.rotationYawHead = rotations[0];
                mc.player.renderYawOffset = rotations[0];
                if (msTimer.hasReached(200L)) {
                    mc.playerController.attackEntity(mc.player, e);
                    msTimer.reset();
                }
            }
        }
    }
}
