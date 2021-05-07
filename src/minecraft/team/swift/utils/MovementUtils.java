package team.swift.utils;

import net.minecraft.client.Minecraft;

public class MovementUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static boolean isMoving() {
        return mc.player.movementInput.moveStrafe != 0.0 || mc.player.movementInput.field_192832_b /* moveForward */ != 0.0;
    }

    public static void setSpeed(double speed) {
        float f = mc.player.movementInput.field_192832_b; // moveForward
        float f1 = mc.player.movementInput.moveStrafe;
        float f2 = mc.player.rotationYaw;

        if (f == 0.0F && f1 == 0.0F) {
            mc.player.motionX = 0.0D;
            mc.player.motionZ = 0.0D;
        } else if (f != 0.0F) {
            if (f1 >= 1.0F) {
                f2 += (f > 0.0F ? -35 : 35);
                f1 = 0.0F;
            } else if (f1 <= -1.0F) {
                f2 += (f > 0.0F ? 35 : -35);
                f1 = 0.0F;
            }

            if (f > 0.0F) {
                f = 1.0F;
            } else if (f < 0.0F) {
                f = -1.0F;
            }
        }

        double d0 = Math.cos(Math.toRadians(f2 + 90.0F));
        double d1 = Math.sin(Math.toRadians(f2 + 90.0F));
        mc.player.motionX = f * speed * d0 + f1 * speed * d1;
        mc.player.motionZ = f * speed * d1 - f1 * speed * d0;
    }
}
