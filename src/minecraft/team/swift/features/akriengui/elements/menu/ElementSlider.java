package team.swift.features.akriengui.elements.menu;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

import team.swift.features.akriengui.elements.Element;
import team.swift.features.akriengui.elements.ModuleButton;
import team.swift.settings.Setting;
import team.swift.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ElementSlider extends Element
{
    public boolean dragging;

    public ElementSlider(ModuleButton iparent, Setting iset)
    {
        this.parent = iparent;
        this.set = iset;
        this.dragging = false;
        super.setup();
    }

    public void tick()
    {
        double d0 = (this.set.getValDouble() - this.set.getMin()) / (this.set.getMax() - this.set.getMin());

        if (d0 < 0.0D)
        {
            d0 = 0.0D;
        }

        this.anim = (float)((double)this.anim + (d0 - (double)this.anim) / 4.0D);
        double d1 = this.set.onlyInt() ? 1000.0D : 100.0D;
        this.anim2 = (float)((double)this.anim2 + ((double)Math.round(this.set.getValDouble() * d1) / d1 - (double)this.anim2) / 2.0D);
    }

    public static double getNormalDouble(double d, int addableNumber)
    {
        return (new BigDecimal(d)).setScale(addableNumber, RoundingMode.HALF_EVEN).doubleValue();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        double d0 = this.set.onlyInt() ? (double)((int)Math.round((double)this.anim2 * 1000.0D)) / 1000.0D : (double)Math.round((double)this.anim2 * 100.0D) / 100.0D;
        String s = "";

        if (this.set.onlyInt())
        {
            s = "" + (int)d0;
        }
        else
        {
            s = "" + getNormalDouble(d0, 1);
        }

        boolean flag = this.isSliderHovered(mouseX, mouseY) || this.dragging;
        Color color = new Color(-13350562);
        RenderUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, new Color(56, 57, 56).getRGB());
        Minecraft.getMinecraft().fontRendererObj.drawString(this.setstrg + " (" + s + ")", (int)(this.x + 8.0D), (int)(this.y + 3.0D), new Color(110, 114, 114).getRGB());
        RenderUtils.drawRect(this.x + 8.0D, this.y + 11.0D, this.x + 8.0D + (this.width - 16.0D), this.y + 12.5D, -15724528);
        RenderUtils.drawRect(this.x + 8.0D, this.y + 11.0D, (double)((float)(this.x + 11.0D + (double)this.anim * (this.width - 21.0D))), this.y + 12.5D, new Color(110, 114, 114).getRGB());
        GL11.glPushMatrix();
        RenderUtils.drawBorderedCircle((float)(this.x + 11.0D + (double)this.anim * (this.width - 21.0D)), (float)(this.y + 12.0D), 3.0F, 1, new Color(110, 114, 114).getRGB(), new Color(130, 134, 134).getRGB());
        GL11.glPopMatrix();

        if (this.dragging)
        {
            double d1 = this.set.getMax() - this.set.getMin();
            double d2 = this.set.getMin() + MathHelper.clamp(((double)mouseX - (this.x + 8.0D)) / (this.width - 16.0D), 0.0D, 1.0D) * d1;
            this.set.setValDouble(d2);
        }
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        if (mouseButton == 0 && this.isSliderHovered(mouseX, mouseY))
        {
            this.dragging = true;
            return true;
        }
        else
        {
            return super.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state)
    {
        this.dragging = false;
    }

    public boolean isSliderHovered(int mouseX, int mouseY)
    {
        return (double)mouseX >= this.x + 6.0D && (double)mouseX <= this.x + 8.0D + (this.width - 13.0D) && (double)mouseY >= this.y + 9.0D && (double)mouseY <= this.y + 15.0D;
    }
}
