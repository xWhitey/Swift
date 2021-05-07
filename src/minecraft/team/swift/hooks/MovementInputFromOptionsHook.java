package team.swift.hooks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInputFromOptions;
import org.lwjgl.input.Keyboard;
import team.swift.features.module.ModuleManager;
import team.swift.features.module.modules.movement.InventoryMove;

import java.util.Objects;

public class MovementInputFromOptionsHook extends MovementInputFromOptions {
    private final GameSettings gameSettings;

    public MovementInputFromOptionsHook(GameSettings gameSettingsIn) {
        super(gameSettingsIn);
        this.gameSettings = gameSettingsIn;
    }

    public void updatePlayerMoveState() {
        this.moveStrafe = 0.0F;
        this.field_192832_b = 0.0F;

        if ((((InventoryMove) Objects.requireNonNull(ModuleManager.getModule(InventoryMove.class))).state
                && (!(Minecraft.getMinecraft().currentScreen instanceof GuiChat) && !(Minecraft.getMinecraft().currentScreen instanceof GuiRepair)
                && !(Minecraft.getMinecraft().currentScreen instanceof GuiEditSign))
                && Minecraft.getMinecraft().player != null && Keyboard.isKeyDown(this.gameSettings.keyBindForward.getKeyCode())) || this.gameSettings.keyBindForward.isKeyDown()) {
            ++this.field_192832_b;
            this.forwardKeyDown = true;
        } else {
            this.forwardKeyDown = false;
        }

        if ((((InventoryMove) Objects.requireNonNull(ModuleManager.getModule(InventoryMove.class))).state
                && (!(Minecraft.getMinecraft().currentScreen instanceof GuiChat) && !(Minecraft.getMinecraft().currentScreen instanceof GuiRepair)
                && !(Minecraft.getMinecraft().currentScreen instanceof GuiEditSign))
                && Minecraft.getMinecraft().player != null && Keyboard.isKeyDown(this.gameSettings.keyBindBack.getKeyCode())) || this.gameSettings.keyBindBack.isKeyDown()) {
            --this.field_192832_b;
            this.backKeyDown = true;
        } else {
            this.backKeyDown = false;
        }

        if ((((InventoryMove) ModuleManager.getModule(InventoryMove.class)).getState()
                && (!(Minecraft.getMinecraft().currentScreen instanceof GuiChat) && !(Minecraft.getMinecraft().currentScreen instanceof GuiRepair)
                && !(Minecraft.getMinecraft().currentScreen instanceof GuiEditSign))
                && Minecraft.getMinecraft().player != null && Keyboard.isKeyDown(this.gameSettings.keyBindLeft.getKeyCode())) || this.gameSettings.keyBindLeft.isKeyDown()) {
            ++this.moveStrafe;
            this.leftKeyDown = true;
        } else {
            this.leftKeyDown = false;
        }

        if ((((InventoryMove) Objects.requireNonNull(ModuleManager.getModule(InventoryMove.class))).state
                && (!(Minecraft.getMinecraft().currentScreen instanceof GuiChat) && !(Minecraft.getMinecraft().currentScreen instanceof GuiRepair)
                && !(Minecraft.getMinecraft().currentScreen instanceof GuiEditSign))
                && Minecraft.getMinecraft().player != null && Keyboard.isKeyDown(this.gameSettings.keyBindRight.getKeyCode())) || this.gameSettings.keyBindRight.isKeyDown()) {
            --this.moveStrafe;
            this.rightKeyDown = true;
        } else {
            this.rightKeyDown = false;
        }

        this.jump = (((InventoryMove) Objects.requireNonNull(ModuleManager.getModule(InventoryMove.class))).state
                && (!(Minecraft.getMinecraft().currentScreen instanceof GuiChat) && !(Minecraft.getMinecraft().currentScreen instanceof GuiRepair)
                && !(Minecraft.getMinecraft().currentScreen instanceof GuiEditSign))
                && Minecraft.getMinecraft().player != null && Keyboard.isKeyDown(this.gameSettings.keyBindJump.getKeyCode())) || this.gameSettings.keyBindJump.isKeyDown();
        this.sneak = this.gameSettings.keyBindSneak.isKeyDown();

        if (this.sneak) {
            this.moveStrafe = (float)((double)this.moveStrafe * 0.3D);
            this.field_192832_b = (float)((double)this.field_192832_b * 0.3D);
        }
    }
}
