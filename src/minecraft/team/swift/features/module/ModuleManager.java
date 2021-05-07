/*
 * Swift: Hacked Client
 * A free open source injection hacked client for Minecraft using Java-agents.
 * https://github.com/xWhitey/Swift
 */

package team.swift.features.module;

import team.swift.features.module.modules.combat.*;
import team.swift.features.module.modules.movement.*;
import team.swift.features.module.modules.player.*;
import team.swift.features.module.modules.render.*;
import team.swift.features.module.modules.world.Timer;

import java.util.ArrayList;
import java.util.Objects;

public class ModuleManager {
    public static ArrayList<Module> modules = new ArrayList<>();

    /**
    * @param module The module we will register.
    */
    public static void registerModule(Module module) {
        modules.add(module);
    }
    public static void registerModule(final Class<? extends Module> moduleClass) {
        try {
            registerModule(moduleClass.newInstance());
        } catch (Throwable ignored) {}
    }

    public static Module getModule(Class<? extends Module> targetClass) {
        for (Module module : Objects.requireNonNull(modules)) {
            if (Objects.requireNonNull(targetClass).equals(Objects.requireNonNull(module.getClass()))) {
                return Objects.requireNonNull(module);
            }
        }

        return null;
    }


    public static Module getModule(String targetName) {
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(targetName)) {
                return module;
            }
        }

        return null;
    }

    /*
    * Register all your modules here.
    */
    public static void registerModules() {
        registerModule(new Fly());
        registerModule(new KillAura());
        registerModule(new NoFall());
        registerModule(new PlayerESP());
        registerModule(new Aim());
        registerModule(new Timer());
        registerModule(new InventoryMove());

        // I really recommend you to register all your modules before HUD and ClickGUI
        registerModule(HUD.class);
        registerModule(ClickGUI.class);
    }
}
