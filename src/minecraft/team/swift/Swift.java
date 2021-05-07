/*
 * Swift: Hacked Client
 * A free open source injection hacked client for Minecraft using Java-agents.
 * https://github.com/xWhitey/Swift
 */

package team.swift;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.profiler.Profiler;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import team.swift.features.module.Module;
import team.swift.features.module.ModuleManager;
import team.swift.hooks.MovementInputFromOptionsHook;
import team.swift.hooks.PlayerControllerMPHook;
import team.swift.hooks.ProfilerHook;
import team.swift.utils.MSTimer;

import java.lang.reflect.Field;

@SuppressWarnings("all")
public class Swift {
    public static final String CLIENT_NAME = "Swift";
    public static final float CLIENT_VERSION = 0.1f;

    public static final Minecraft mc = Minecraft.getMinecraft();

    private static final MSTimer msTimer = new MSTimer();

    private static final Logger logger = LogManager.getLogger(CLIENT_NAME);

    /*
    * This is needed for AttackEvent
    */
    public static synchronized void redefineController() {
        if (mc.world != null && mc.player != null && !mc.isSingleplayer()) {
            if (!(mc.playerController instanceof PlayerControllerMPHook)) {
                logger.info("Changing the type of playerController field to PlayerControllerMPHook (just redefine)");
                mc.playerController = new PlayerControllerMPHook(mc, mc.player.connection);
            }
            if (!(mc.playerController instanceof PlayerControllerMPHook)) {
                try {
                    logger.info("Changing the type of playerController field to PlayerControllerMPHook (using reflections)");
                    Field f = Minecraft.class.getDeclaredField("c"); // playerController
                    f.setAccessible(true);
                    PlayerControllerMP pcmpHook = new PlayerControllerMPHook(mc, mc.player.connection);
                    f.set(mc, (PlayerControllerMP) pcmpHook);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    * Fake bind-system
    */
    public static synchronized void initBinds() {
        int i = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
        if (Keyboard.getEventKeyState() && Minecraft.getMinecraft().currentScreen == null && i != 0) {
            Keyboard.enableRepeatEvents(false);
            for (Module module : ModuleManager.modules) {
                if (module.getKey() == i) {
                    if (msTimer.hasReached(400L)) {
                        module.toggle(); // module toggle
                        msTimer.reset();
                    }
                }
            }
        }
    }

    /*
    * This is needed for InventoryMove
    */
    public static synchronized void redefineMovementInput() {
        if (mc.world != null && mc.player != null) {
            if (!(mc.player.movementInput instanceof MovementInputFromOptionsHook)) {
                logger.info("Changing the type of movementInput field to MovementInputFromOptionsHook");
                mc.player.movementInput = new MovementInputFromOptionsHook(mc.gameSettings);
            }
        }
    }

    /*
    * This is needed for Render2DEvent
    */
    public static synchronized void redefineProfiler() {
        try {
            logger.info("Changing the type of mcProfiler field to ProfilerHook");
            Field f = Minecraft.class.getDeclaredField("B"); // mcProfiler
            f.setAccessible(true);
            FieldUtils.removeFinalModifier(f);
            Profiler pf = new ProfilerHook();
            f.set(mc, (Profiler) pf);
            if (mc.mcProfiler instanceof ProfilerHook) {
                logger.info("Successfully changed the type of mcProfiler field to ProfilerHook!");
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
