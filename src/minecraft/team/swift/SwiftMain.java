/*
 * Swift: Hacked Client
 * A free open source injection hacked client for Minecraft using Java-agents.
 * https://github.com/xWhitey/Swift
 */

package team.swift;

import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.swift.event.EventManager;
import team.swift.event.events.impl.UpdateEvent;
import team.swift.features.module.ModuleManager;
import team.swift.hooks.MovementInputFromOptionsHook;
import team.swift.hooks.PlayerControllerMPHook;
import team.swift.javassist.AgentClassTransformer;
import team.swift.settings.SettingsManager;

import java.lang.instrument.Instrumentation;

@SuppressWarnings("all")
public class SwiftMain {
    private static final Logger logger = LogManager.getLogger(Swift.CLIENT_NAME);

    public static SettingsManager settingManager;
    public static Minecraft mc;

    private static boolean hasPrinted = false;
    private static boolean hasPrinted2 = false;

    /*
    * Main method of Swift.
    */
    public static synchronized void agentmain(String agentArgs, Instrumentation inst) {
        logger.info("Welcome to " + Swift.CLIENT_NAME + " v" + Swift.CLIENT_VERSION); // User welcome message
        inst.addTransformer(AgentClassTransformer.INSTANCE);
        mc = Minecraft.getMinecraft();
        settingManager = new SettingsManager();
        Swift.redefineProfiler();
        ModuleManager.registerModules(); // Registering all modules
        /*
        * Just new thread, I don't know how to describe this.
        */
        new Thread(() -> {
            while (true) {
                if (mc.playerController instanceof PlayerControllerMPHook && !hasPrinted) {
                    logger.info("Successfully changed the type of playerController field to PlayerControllerMPHook!");
                    hasPrinted = true;
                }
                if (mc.player.movementInput instanceof MovementInputFromOptionsHook && !hasPrinted2) {
                    logger.info("Successfully changed the type of movementInput field to MovementInputFromOptionsHook!");
                    hasPrinted2 = true;
                }
                Swift.initBinds(); // Calling bind-system
                Swift.redefineController(); // Should be called in a loop, because playerController redefines after rejoin
                Swift.redefineMovementInput(); // Should be also called in a loop, because movementInput can self-redefine after death/rejoin
                EventManager.call(new UpdateEvent()); // Calling fake UpdateEvent
                /*
                * Fake-wait due to onUpdate calls every 25 millis, so we are making fake onUpdate
                */
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start(); // Thread start.
    }
}
