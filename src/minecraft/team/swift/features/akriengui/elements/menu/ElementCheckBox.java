package team.swift.features.akriengui.elements.menu;

import java.awt.Color;

import team.swift.features.akriengui.elements.Element;
import team.swift.settings.Setting;
import team.swift.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import team.swift.features.akriengui.elements.ModuleButton;

public class ElementCheckBox extends Element
{
    public ElementCheckBox(ModuleButton iparent, Setting iset)
    {
        this.parent = iparent;
        this.set = iset;
        super.setup();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height,  new Color(56, 57, 56).getRGB());
        GL11.glPushMatrix();
        RenderUtils.setColor(new Color(56, 57, 56));
        int j = (int)(this.x + 28.0D);
        int k = (int)(this.y + 5.5D);
        Minecraft.getMinecraft().fontRendererObj.drawString(this.setstrg, j, k, Color.WHITE.getRGB());//new Color(110, 114, 114).getRGB());
        GL11.glPopMatrix();
        RenderUtils.drawBorderedRect((float)this.x + 11.2F, (float)this.y + 7.0F, (float)this.x + 17.0F, (float)this.y + 11.0F, 1.0F, new Color(110, 114, 114).getRGB(), this.anim < 4.5F ? new Color(110, 114, 114).getRGB() : new Color(110, 114, 114).getRGB());
        GL11.glPushMatrix();
        RenderUtils.drawBorderedCircle((float)((int)this.x + 11), (float)((int)this.y + 9), 2.0F, 1, new Color(110, 114, 114).getRGB(), new Color(110, 114, 114).getRGB());
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        RenderUtils.drawBorderedCircle((float)((int)this.x + 17), (float)((int)this.y + 9), 2.0F, 1, new Color(110, 114, 114).getRGB(), new Color(110, 114, 114).getRGB());
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();

        if (this.anim > 0.0F)
        {
            GL11.glPushMatrix();
            RenderUtils.drawBorderedCircle((float)this.x + 11.0F + this.anim, (float)this.y + 9.0F, 3.0F, 1, new Color(110, 114, 114).getRGB(), -1);
            GL11.glPopMatrix();
            RenderUtils.drawCircle228((float)this.x + 11.0F + this.anim, (float)this.y + 9.0F, 3.0F, 5, new Color(110, 114, 114).getRGB(), new Color(110, 114, 114).getRGB(), (int)(this.anim * 60.0F));
        }
        else
        {
            RenderUtils.drawBorderedCircle((float)this.x + 11.0F + this.anim, (float)this.y + 9.0F, 3.0F, 1, new Color(110, 114, 114).getRGB(), new Color(110, 114, 114).getRGB());
        }

        GL11.glPopMatrix();
    }

    public void tick()
    {
        if (this.set.getValBoolean())
        {
            this.anim = (float)((double)this.anim + 0.55D);
        }
        else
        {
            this.anim = (float)((double)this.anim - 0.55D);
        }

        if (this.anim < 0.0F)
        {
            this.anim = 0.0F;
        }

        if (this.anim > 6.0F)
        {
            this.anim = 6.0F;
        }
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        if (mouseButton == 0 && this.isCheckHovered(mouseX, mouseY))
        {
            this.set.setValBoolean(!this.set.getValBoolean());
            return true;
        }
        else
        {
            return super.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    public boolean isCheckHovered(int mouseX, int mouseY)
    {
        return (double)mouseX >= this.x + 7.5D && (double)mouseX <= this.x + 21.0D && (double)mouseY >= this.y + 7.0D && (double)mouseY <= this.y + 14.5D;
    }
}
