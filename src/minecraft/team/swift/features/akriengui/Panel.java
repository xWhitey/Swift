package team.swift.features.akriengui;

import java.awt.Color;
import java.util.ArrayList;

import team.swift.features.akriengui.elements.ModuleButton;
import team.swift.utils.RenderUtils;
import net.minecraft.client.Minecraft;

public class Panel
{
    public String title;
    public double x;
    public double y;
    private double x2;
    private double y2;
    public double width;
    public double height;
    public double animHeight;
    public boolean dragging;
    public boolean extended;
    public boolean visible;
    public ArrayList<ModuleButton> Elements = new ArrayList<ModuleButton>();
    public AkrienClickGUI clickgui;

    public Panel(String ititle, double ix, double iy, double iwidth, double iheight, boolean iextended, AkrienClickGUI parent)
    {
        this.title = ititle;
        this.x = ix;
        this.y = iy;
        this.width = iwidth;
        this.height = iheight;
        this.extended = iextended;
        this.dragging = false;
        this.visible = true;
        this.clickgui = parent;
        this.setup();
    }

    public void setup()
    {
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            if (this.dragging)
            {
                this.x = this.x2 + (double)mouseX;
                this.y = this.y2 + (double)mouseY;
            }

            Color color = new Color(-13350562);
            int i = (new Color(color.getRed(), color.getGreen(), color.getBlue(), 170)).getRGB();
            RenderUtils.drawOctagon((float)this.x, (float)this.y, (float)this.width, (float)this.height + 1.0F, 2.0F, 12.0F, new Color(64, 67, 65).getRGB());//-2039584);
            int k = (int)this.x + 5;
            int l = (int)(this.y + 10.0D);
            Minecraft.getMinecraft().fontRendererObj.drawString(this.title, k, l, Color.WHITE.getRGB());//-13158601);

            if (this.extended && !this.Elements.isEmpty())
            {
                double d0 = this.y + this.height;
                int j = -1156246251;

                for (ModuleButton modulebutton : this.Elements)
                {
                    RenderUtils.drawRect((int) this.x, (int) d0, (int) this.x + (int) this.width, (int) d0 + (int) modulebutton.height + 1, new Color(64, 67, 65).getRGB());//-1118482);

                    if (this.Elements.get(0) == modulebutton)
                    {
                        RenderUtils.drawGradient(this.x, d0, this.x + this.width, d0 + 10.0D, new Color(32, 32, 32).getRGB()/*-4342339*/, new Color(64, 64, 64).getRGB()/*15658734*/);
                    }

                    modulebutton.x = this.x + 2.0D;
                    modulebutton.y = d0;
                    modulebutton.width = this.width - 4.0D;
                    modulebutton.drawScreen(mouseX, mouseY, partialTicks);
                    d0 += modulebutton.height + 1.0D;
                }
            }
        }
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        if (!this.visible)
        {
            return false;
        }
        else if (mouseButton == 0 && this.isHovered(mouseX, mouseY))
        {
            this.x2 = this.x - (double)mouseX;
            this.y2 = this.y - (double)mouseY;
            this.dragging = true;
            return true;
        }
        else if (mouseButton == 1 && this.isHovered(mouseX, mouseY))
        {
            this.extended = !this.extended;
            return true;
        }
        else
        {
            if (this.extended)
            {
                for (ModuleButton modulebutton : this.Elements)
                {
                    if (modulebutton.mouseClicked(mouseX, mouseY, mouseButton))
                    {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state)
    {
        if (this.visible)
        {
            if (state == 0)
            {
                this.dragging = false;
            }
        }
    }

    public boolean isHovered(int mouseX, int mouseY)
    {
        return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y && (double)mouseY <= this.y + this.height;
    }
}
