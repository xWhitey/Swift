package team.swift.features.akriengui.elements.menu;

import java.awt.Color;

import team.swift.features.akriengui.elements.Element;
import team.swift.features.akriengui.elements.ModuleButton;
import team.swift.settings.Setting;
import team.swift.utils.RenderUtils;
import net.minecraft.client.Minecraft;

public class ElementComboBox extends Element
{
    public ElementComboBox(ModuleButton iparent, Setting iset)
    {
        this.parent = iparent;
        this.set = iset;
        super.setup();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        RenderUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height,  new Color(56, 57, 56).getRGB());
        //RenderUtils.drawGradientSideways(this.x, this.y, this.x + this.width - 100.0D, this.y + this.height, -5460820, 14737632);
        RenderUtils.drawOctagon((float)this.x + 8.0F, (float)this.y + 1.0F, (float)this.width - 16.0F, 14.0F, 1.0F, 6.0F, new Color(159, 160, 158).getRGB());

        if (this.comboextended)
        {
            RenderUtils.drawRect(this.x + 12.0D, this.y + 15.0D, this.x + this.width - 12.0D, this.y + this.height, new Color(56, 57, 56).getRGB());
        }

        Minecraft.getMinecraft().fontRendererObj.drawString(this.setstrg, ((int)(this.x + this.width / 2.0D - (double)(Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.setstrg) / 2))), ((int)(this.y + 4.0D)), new Color(110, 114, 114).getRGB());

        if (this.comboextended)
        {
            double d0 = this.y + 16.0D;

            for (Object sw : this.set.getOptions())
            {
                String s = (String) sw;
                String s1 = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
                Minecraft.getMinecraft().fontRendererObj.drawString(s1, (int)(this.x + this.width / 2.0D - (double)(Minecraft.getMinecraft().fontRendererObj.getStringWidth(s1) / 2)), (int)(d0 + 1.0D), s.equalsIgnoreCase(this.set.getValString()) ? -1 /*-16746560*/ : new Color(110, 114, 114).getRGB());//-12105913);

                d0 += (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 4);
            }
        }
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        if (mouseButton == 0)
        {
            if (this.isButtonHovered(mouseX, mouseY))
            {
                this.comboextended = !this.comboextended;
                return true;
            }

            if (!this.comboextended)
            {
                return false;
            }

            double d0 = this.y + 16.0D;

            for (Object sw : this.set.getOptions())
            {
                String s = (String) sw;
                if ((double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= d0 && (double)mouseY <= d0 + (double)Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2.0D)
                {
                    if (this.clickgui != null && this.clickgui.setmgr != null)
                    {
                        this.clickgui.setmgr.getSettingByName(this.set.getName()).setValString(s.toLowerCase());
                    }

                    return true;
                }

                d0 += (double)(Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 6);
            }
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public boolean isButtonHovered(int mouseX, int mouseY)
    {
        return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y && (double)mouseY <= this.y + 15.0D;
    }
}
