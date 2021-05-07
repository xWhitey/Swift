package team.swift.features.akriengui.elements;

import team.swift.features.akriengui.AkrienClickGUI;
import net.minecraft.client.Minecraft;
import team.swift.settings.Setting;

public class Element
{
    public AkrienClickGUI clickgui;
    public ModuleButton parent;
    public Setting set;
    public double offset;
    public double x;
    public double y;
    public double width;
    public double height;
    public float anim;
    public float anim2;
    public String setstrg;
    public boolean comboextended;

    public void setup()
    {
        this.clickgui = this.parent.parent.clickgui;
        this.anim = 0.0F;
        this.anim2 = 0.0F;
    }

    public void tick()
    {
    }

    public void update()
    {
        this.x = this.parent.x + this.parent.width + 2.0D;
        this.y = this.parent.y + this.offset;
        this.width = this.parent.width;
        this.height = 16.0D;
        String s = this.set.getSimpleName();

        if (this.set.isCheck())
        {
            this.setstrg = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
            double d0 = this.x + this.width - (double)Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.setstrg);

            if (d0 < this.x + 13.0D)
            {
                this.width += this.x + 13.0D - d0 + 1.0D;
            }
        }
        else if (this.set.isCombo())
        {
            this.height = this.comboextended ? (double)(this.set.getOptions().length * (Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 3)) + this.height : this.height;
            this.setstrg = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
            int j = Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.setstrg);

            for (Object sw : this.set.getOptions())
            {
                String s1 = (String) sw;
                int i = Minecraft.getMinecraft().fontRendererObj.getStringWidth(s1);

                if (i > j)
                {
                    j = i;
                }
            }

            double d1 = this.x + this.width - (double)j;

            if (d1 < this.x)
            {
                this.width += this.x - d1 + 1.0D;
            }
        }
        else if (this.set.isSlider())
        {
            this.setstrg = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
            String s2 = "" + (double)Math.round(this.set.getValDouble() * 100.0D) / 100.0D;
            String s3 = "" + (double)Math.round(this.set.getMax() * 100.0D) / 100.0D;
            double d2 = this.x + this.width - (double)Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.setstrg) - (double)Minecraft.getMinecraft().fontRendererObj.getStringWidth(s3) - 4.0D;

            if (d2 < this.x)
            {
                this.width += this.x - d2 + 1.0D;
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        return this.isHovered(mouseX, mouseY);
    }

    public void mouseReleased(int mouseX, int mouseY, int state)
    {
    }

    public boolean isHovered(int mouseX, int mouseY)
    {
        return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y && (double)mouseY <= this.y + this.height;
    }
}
