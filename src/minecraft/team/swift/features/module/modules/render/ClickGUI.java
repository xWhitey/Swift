package team.swift.features.module.modules.render;

import team.swift.features.akriengui.AkrienClickGUI;
import team.swift.features.module.Module;
import team.swift.features.module.ModuleCategory;
import org.lwjgl.input.Keyboard;

/*
* This shit is skidded, because we are using Fals3R's ClickGUI. :shrug:
* Was originally created by Fals3R 3 years ago and deobfuscated by xWhitey on 24.03.2021
*/
public class ClickGUI extends Module {
    public ClickGUI() {
        super("ClickGUI", ModuleCategory.RENDER, Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(new AkrienClickGUI());
        setState(false);
    }
}
