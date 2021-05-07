package team.swift.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/*
 * This shit is skidded, because we are using Fals3R's ClickGUI. :shrug:
 * Was originally created by Fals3R 3 years ago and deobfuscated by xWhitey on 24.03.2021
 */
@SuppressWarnings("all")
public class RenderUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void glRenderStart()
    {
        GL11.glPushMatrix();
        GL11.glPushAttrib(1048575);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    public static void glRenderStop()
    {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static float convertColor(int count, int color)
    {
        return (float)(color >> count & 255) / 255.0F;
    }

    public static void setColor(Color c)
    {
        GL11.glColor4d((double)((float)c.getRed() / 255.0F), (double)((float)c.getGreen() / 255.0F), (double)((float)c.getBlue() / 255.0F), (double)((float)c.getAlpha() / 255.0F));
    }

    public static void drawGradient(double x, double y, double x2, double y2, int col1, int col2)
    {
        float f = (float)(col1 >> 24 & 255) / 255.0F;
        float f1 = (float)(col1 >> 16 & 255) / 255.0F;
        float f2 = (float)(col1 >> 8 & 255) / 255.0F;
        float f3 = (float)(col1 & 255) / 255.0F;
        float f4 = (float)(col2 >> 24 & 255) / 255.0F;
        float f5 = (float)(col2 >> 16 & 255) / 255.0F;
        float f6 = (float)(col2 >> 8 & 255) / 255.0F;
        float f7 = (float)(col2 & 255) / 255.0F;
        glRenderStart();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glColor4f(f5, f6, f7, f4);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glColor4d(1.0D, 1.0D, 1.0D, 1.0D);
        glRenderStop();
    }

    public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2)
    {
        float f = (float)(col1 >> 24 & 255) / 255.0F;
        float f1 = (float)(col1 >> 16 & 255) / 255.0F;
        float f2 = (float)(col1 >> 8 & 255) / 255.0F;
        float f3 = (float)(col1 & 255) / 255.0F;
        float f4 = (float)(col2 >> 24 & 255) / 255.0F;
        float f5 = (float)(col2 >> 16 & 255) / 255.0F;
        float f6 = (float)(col2 >> 8 & 255) / 255.0F;
        float f7 = (float)(col2 & 255) / 255.0F;
        glRenderStart();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);
        GL11.glColor4f(f5, f6, f7, f4);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glShadeModel(GL11.GL_FLAT);
        glRenderStop();
    }

    public static void drawRect(double x, double y, double d, double e, int color)
    {
        float f = (float)(color >> 24 & 255) / 255.0F;
        float f1 = (float)(color >> 16 & 255) / 255.0F;
        float f2 = (float)(color >> 8 & 255) / 255.0F;
        float f3 = (float)(color & 255) / 255.0F;
        glRenderStart();
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(d, y);
        GL11.glVertex2d(d, e);
        GL11.glVertex2d(x, e);
        GL11.glEnd();
        glRenderStop();
    }

    public static void drawBorderedRect(float xPos, float yPos, float width, float height, float lineWidth, int lineColor, int bgColor)
    {
        drawRect(xPos, yPos, width, height, bgColor);
        float f = (float)(lineColor >> 24 & 255) / 255.0F;
        float f1 = (float)(lineColor >> 16 & 255) / 255.0F;
        float f2 = (float)(lineColor >> 8 & 255) / 255.0F;
        float f3 = (float)(lineColor & 255) / 255.0F;
        glRenderStart();
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glLineWidth(lineWidth);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d(xPos, yPos);
        GL11.glVertex2d(width, yPos);
        GL11.glVertex2d(width, yPos);
        GL11.glVertex2d(width, height);
        GL11.glVertex2d(width, height);
        GL11.glVertex2d(xPos, height);
        GL11.glVertex2d(xPos, height);
        GL11.glVertex2d(xPos, yPos);
        GL11.glEnd();
        glRenderStop();
    }

    public static void drawOctagon(float xPos, float yPos, float width, float height, float length, float angle, int color)
    {
        float f = convertColor(24, color);
        float f1 = convertColor(16, color);
        float f2 = convertColor(8, color);
        float f3 = convertColor(0, color);
        glRenderStart();
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glVertex2d((xPos + length), yPos);
        GL11.glVertex2d((xPos + width - length), yPos);
        GL11.glVertex2d((xPos + width - length), yPos);
        GL11.glVertex2d((xPos + width), (yPos + height / 2.0F - angle));
        GL11.glVertex2d((xPos + width), (yPos + height / 2.0F - angle));
        GL11.glVertex2d((xPos + width), (yPos + height / 2.0F + angle));
        GL11.glVertex2d((double)(xPos + width), (yPos + height / 2.0F + angle));
        GL11.glVertex2d((double)(xPos + width - length), (double)(yPos + height));
        GL11.glVertex2d((double)(xPos + width - length), (double)(yPos + height));
        GL11.glVertex2d((double)(xPos + length), (double)(yPos + height));
        GL11.glVertex2d((double)(xPos + length), (double)(yPos + height));
        GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F + angle));
        GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F + angle));
        GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F - angle));
        GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F - angle));
        GL11.glVertex2d((double)(xPos + length), (double)yPos);
        GL11.glEnd();
        glRenderStop();
    }

    public static void drawImage(ResourceLocation image, int x, int y, int width, int height)
    {
        new ScaledResolution(mc);
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, width, height, (float)width, (float)height);
        GL11.glPopMatrix();
    }

    public static void drawImage(ResourceLocation image, int x, int y, int width, int height, Color color)
    {
        new ScaledResolution(Minecraft.getMinecraft());
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f((float)color.getRed() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getRed() / 255.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, width, height, (float)width, (float)height);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public static void drawBorderedCircle(float x, float y, float radius, int lineWidth, int outsideC, int insideC)
    {
        drawCircle(x, y, radius, insideC);
        drawUnfilledCircle(x, y, radius, (float)lineWidth, outsideC);
    }

    public static void drawCircle228(float x, float y, float radius, int lineWidth, int outsideC, int insideC, int jopaSlona)
    {
        drawCircle228(x, y, radius, insideC, jopaSlona);
    }

    public static void drawUnfilledCircle(float x, float y, float radius, float lineWidth, int color)
    {
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        float f3 = (float)(color >> 24 & 255) / 255.0F;
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
        GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_NICEST);
        GlStateManager.enableBlend();
        GL11.glColor4f(f, f1, f2, f3);
        GL11.glLineWidth(lineWidth);
        GL11.glBegin(GL11.GL_LINE_LOOP);

        for (int i = 0; i <= 360; ++i)
        {
            GL11.glVertex2d((double)x + Math.sin((double)i * Math.PI / 180.0D) * (double)radius, (double)y + Math.cos((double)i * Math.PI / 180.0D) * (double)radius);
        }

        GL11.glEnd();
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_DONT_CARE);
        GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_DONT_CARE);
        GlStateManager.disableBlend();
    }

    public static void drawCircle228(float x, float y, float radius, int color, int jopaSlona)
    {
        float f = (float)(color >> 24 & 255) / 255.0F;
        float f1 = (float)(color >> 16 & 255) / 255.0F;
        float f2 = (float)(color >> 8 & 255) / 255.0F;
        float f3 = (float)(color & 255) / 255.0F;
        boolean flag = GL11.glIsEnabled(GL11.GL_BLEND);
        boolean flag1 = GL11.glIsEnabled(GL11.GL_LINE_SMOOTH);
        boolean flag2 = GL11.glIsEnabled(GL11.GL_TEXTURE_2D);

        if (!flag)
        {
            GL11.glEnable(GL11.GL_BLEND);
        }

        if (!flag1)
        {
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
        }

        if (flag2)
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
        }

        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glLineWidth(2.5F);
        GL11.glBegin(GL11.GL_LINE_STRIP);

        for (int i = 0; i <= jopaSlona; ++i)
        {
            GL11.glVertex2d((double)x + Math.sin((double)i * Math.PI / 180.0D) * (double)radius, (double)y + Math.cos((double)i * Math.PI / 180.0D) * (double)radius);
        }

        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);

        if (flag2)
        {
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }

        if (!flag1)
        {
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
        }

        if (!flag)
        {
            GL11.glDisable(GL11.GL_BLEND);
        }
    }

    public static void drawCircle(float x, float y, float radius, int color)
    {
        float f = (float)(color >> 24 & 255) / 255.0F;
        float f1 = (float)(color >> 16 & 255) / 255.0F;
        float f2 = (float)(color >> 8 & 255) / 255.0F;
        float f3 = (float)(color & 255) / 255.0F;
        boolean flag = GL11.glIsEnabled(GL11.GL_BLEND);
        boolean flag1 = GL11.glIsEnabled(GL11.GL_LINE_SMOOTH);
        boolean flag2 = GL11.glIsEnabled(GL11.GL_TEXTURE_2D);

        if (!flag)
        {
            GL11.glEnable(GL11.GL_BLEND);
        }

        if (!flag1)
        {
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
        }

        if (flag2)
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
        }

        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(GL11.GL_POLYGON);

        for (int i = 0; i <= 360; ++i)
        {
            GL11.glVertex2d((double)x + Math.sin((double)i * Math.PI / 180.0D) * (double)radius, (double)y + Math.cos((double)i * Math.PI / 180.0D) * (double)radius);
        }

        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);

        if (flag2)
        {
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }

        if (!flag1)
        {
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
        }

        if (!flag)
        {
            GL11.glDisable(GL11.GL_BLEND);
        }
    }

    public static void enableScissoring()
    {
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
    }

    public static void doGlScissor(int x, int y, int width, int height)
    {
        if (x != width && y != height)
        {
            Minecraft minecraft = Minecraft.getMinecraft();
            int i = 1;

            GL11.glScissor(x * i, minecraft.displayHeight - (y + height) * i, width * i, height * i);
        }
    }

    public static void drawLines(AxisAlignedBB mask)
    {
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
        GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
        GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
        GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
        GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
        GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
        GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
        GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
        GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3d(mask.minX, mask.maxY, mask.minZ);
        GL11.glVertex3d(mask.maxX, mask.maxY, mask.maxZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3d(mask.maxX, mask.maxY, mask.minZ);
        GL11.glVertex3d(mask.minX, mask.maxY, mask.maxZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3d(mask.minX, mask.minY, mask.minZ);
        GL11.glVertex3d(mask.maxX, mask.minY, mask.maxZ);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3d(mask.maxX, mask.minY, mask.minZ);
        GL11.glVertex3d(mask.minX, mask.minY, mask.maxZ);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    public static void entityESPBox(Entity entity, float r, float g, float b, float a)
    {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glColor4d((double)r, (double)g, (double)b, (double)a);
        RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(entity.getRenderBoundingBox().minX - 0.05D - entity.posX + (entity.posX - TileEntityRendererDispatcher.staticPlayerX), entity.getRenderBoundingBox().minY - entity.posY + (entity.posY - TileEntityRendererDispatcher.staticPlayerY), entity.getRenderBoundingBox().minZ - 0.05D - entity.posZ + (entity.posZ - TileEntityRendererDispatcher.staticPlayerZ), entity.getRenderBoundingBox().maxX + 0.05D - entity.posX + (entity.posX - TileEntityRendererDispatcher.staticPlayerX), entity.getRenderBoundingBox().maxY + 0.1D - entity.posY + (entity.posY - TileEntityRendererDispatcher.staticPlayerY), entity.getRenderBoundingBox().maxZ + 0.05D - entity.posZ + (entity.posZ - TileEntityRendererDispatcher.staticPlayerZ)), r, g, b, a);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
    }
}
