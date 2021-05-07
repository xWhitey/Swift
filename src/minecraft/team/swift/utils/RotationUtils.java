package team.swift.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/* Created bu katch & xWhitey on 05.05.2021 */
public class RotationUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();

    /**
    * @author xWhitey
    * @description Something like set player rotation only server-sidely
    * @reason Impossible to emulate server-side rotations in a different way than this without PacketEvent.
    */
    public static void setTargetRotation(float yaw, float pitch) {
        mc.player.connection.sendPacket(new CPacketPlayer.Rotation(yaw, pitch, mc.player.onGround));
    }

    /**
     * @author katch
     * @param entity The entity we will aim (only will get needed rotations for aim)
     */
    public static float[] getRotation(Entity entity) {
        final Vec3d eyesPos = new Vec3d(mc.player.posX + (Math.random() / 10), mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ + (Math.random() / 10));
        final double diffX = entity.getPositionVector().xCoord - eyesPos.xCoord;
        final double diffY = entity.getPositionVector().yCoord - eyesPos.yCoord;
        final double diffZ = entity.getPositionVector().zCoord - eyesPos.zCoord;
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f);
        float pitch = MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(diffY, diffXZ))) - 10);

        final float f = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
        final float gcd = f * f * f * 1.2F;
        yaw -= yaw % gcd;
        pitch -= pitch % gcd;

        return new float[]{yaw, pitch};
    }
}
