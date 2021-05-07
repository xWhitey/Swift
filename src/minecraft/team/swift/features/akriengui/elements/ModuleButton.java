package team.swift.features.akriengui.elements;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import team.swift.SwiftMain;
import team.swift.features.akriengui.Panel;
import team.swift.features.akriengui.elements.menu.ElementCheckBox;
import team.swift.features.akriengui.elements.menu.ElementComboBox;
import team.swift.features.module.Module;
import team.swift.settings.Setting;
import team.swift.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import team.swift.features.akriengui.elements.menu.ElementSlider;

public class ModuleButton
{
    public Module func;
    public ArrayList<Element> menuelements;
    public Panel parent;
    public double x;
    public double y;
    public double width;
    public double height;
    public boolean extended = false;
    public boolean listening = false;

    public ModuleButton(Module ifunc, Panel pl)
    {
        this.func = ifunc;
        this.height = 18;
        this.parent = pl;
        this.menuelements = new ArrayList<>();

        if (SwiftMain.settingManager.getSettingsByMod(ifunc) != null)
        {
            for (Setting setting : SwiftMain.settingManager.getSettingsByMod(ifunc))
            {
                if (setting.isCheck())
                {
                    this.menuelements.add(new ElementCheckBox(this, setting));
                }
                else if (setting.isSlider())
                {
                    this.menuelements.add(new ElementSlider(this, setting));
                }
                else if (setting.isCombo())
                {
                    this.menuelements.add(new ElementComboBox(this, setting));
                }
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        if (this.isHovered(mouseX, mouseY))
        {
            RenderUtils.drawRect((int) this.x - 2, (int) this.y, (int) this.x + (int) this.width + 2, (int) this.y + (int) this.height + 1, 572466736);
        }

        Minecraft.getMinecraft().fontRendererObj.drawString(this.func.getName(), (int)(this.x + 4.0D), (int)(this.y - 2.0D + this.height / 2.0D), this.func.getState() ? Color.WHITE.getRGB() : new Color(110, 114, 114).getRGB());

        if (SwiftMain.settingManager.getSettingsByMod(this.func) != null)
        {
            GL11.glPushMatrix();
            int k = (int)(this.x + this.width - 6.0D);
            Minecraft.getMinecraft().fontRendererObj.drawString("-", k, (int)(this.y - 2.0D + this.height / 2.0D), this.func.getState() ? Color.WHITE.getRGB() : new Color(110, 114, 114).getRGB());
            GL11.glPopMatrix();
        }
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        if (!this.isHovered(mouseX, mouseY))
        {
            return false;
        }
        else
        {
            if (mouseButton == 0)
            {
                this.func.toggle();
            }
            else if (mouseButton == 1)
            {
                if (this.menuelements != null && this.menuelements.size() > 0)
                {
                    boolean flag = !this.extended;
                    this.extended = flag;
                }
            }
            else if (mouseButton == 2)
            {
                this.listening = true;
            }

            return true;
        }
    }

    public boolean keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (this.listening)
        {
            if (keyCode != 1)
            {
                this.func.setKey(keyCode);
            }
            else
            {
                this.func.setKey(0);
            }

            this.listening = false;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isHovered(int mouseX, int mouseY)
    {
        return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y && (double)mouseY <= this.y + this.height;
    }
}
