package team.swift.features.module.modules.movement;

import org.lwjgl.input.Keyboard;
import team.swift.features.module.Module;
import team.swift.features.module.ModuleCategory;

public class InventoryMove extends Module {
    public InventoryMove() {
        super("InventoryMove", ModuleCategory.MOVEMENT, Keyboard.KEY_NONE);
    }
}
