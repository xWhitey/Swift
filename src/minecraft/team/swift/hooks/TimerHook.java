package team.swift.hooks;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

@SuppressWarnings("all")
public class TimerHook {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public TimerHook() {

    }

    public static float getTimerSpeed() {
        Class<Minecraft> mcClass = Minecraft.class;
        try {
            Field timerField = mcClass.getDeclaredField("Y"); // timer
            timerField.setAccessible(true);
            FieldUtils.removeFinalModifier(timerField);
            Object timer = timerField.get(mc);
            Class<Timer> timerClass = (Class<Timer>) timer.getClass();
            Field tickLengthField = timerClass.getDeclaredField("e"); // tickLength or field_194149_e
            tickLengthField.setAccessible(true);
            return tickLengthField.getFloat(timer);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return 0f;
    }

    public static void setTimerSpeed(float timerSpeed) {
        Class<Minecraft> mcClass = Minecraft.class;
        try {
            Field timerField = mcClass.getDeclaredField("Y"); // timer
            timerField.setAccessible(true);
            FieldUtils.removeFinalModifier(timerField);
            Object timer = timerField.get(mc);
            Class<Timer> timerClass = (Class<Timer>) timer.getClass();
            Field tickLengthField = timerClass.getDeclaredField("e"); // tickLength or field_194149_e
            tickLengthField.setAccessible(true);
            tickLengthField.setFloat(timer, 50.0F / timerSpeed);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setTimerSpeed(double timerSpeed) {
        Class<Minecraft> mcClass = Minecraft.class;
        try {
            Field timerField = mcClass.getDeclaredField("Y"); // timer
            timerField.setAccessible(true);
            FieldUtils.removeFinalModifier(timerField);
            Object timer = timerField.get(mc);
            Class<Timer> timerClass = (Class<Timer>) timer.getClass();
            Field tickLengthField = timerClass.getDeclaredField("e"); // tickLength or field_194149_e
            tickLengthField.setAccessible(true);
            tickLengthField.setFloat(timer, (float) (50.0D / timerSpeed));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
