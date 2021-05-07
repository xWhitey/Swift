package team.swift.features.akriengui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import team.swift.SwiftMain;
import team.swift.features.module.Module;
import team.swift.features.module.ModuleCategory;
import team.swift.features.module.ModuleManager;
import team.swift.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;
import team.swift.features.akriengui.elements.Element;
import team.swift.features.akriengui.elements.ModuleButton;
import team.swift.features.akriengui.elements.menu.ElementSlider;
import team.swift.settings.SettingsManager;

/*
 * This shit is skidded, because we are using Fals3R's ClickGUI. :shrug:
 * Was originally created by Fals3R 3 years ago and deobfuscated by xWhitey on 24.03.2021
 */
public class AkrienClickGUI extends GuiScreen {
    public static ArrayList<Panel> panels;
    public static ArrayList<Panel> rpanels;
    private ModuleButton mb = null;
    public SettingsManager setmgr = SwiftMain.settingManager;

    public AkrienClickGUI()
    {
        panels = new ArrayList<>();
        double d0 = 105.0D;
        double d1 = 25.0D;
        double d2 = 10.0D;
        double d3 = 10.0D;

        for (final ModuleCategory category : ModuleCategory.values())
        {
            String s = Character.toUpperCase(category.name().toLowerCase().charAt(0)) + category.name().toLowerCase().substring(1);
            panels.add(new Panel(s, d2, d3, d0, d1, true, this)
            {
                public void setup()
                {
                    for (Module function : ModuleManager.modules)
                    {
                        if (function.getCategory().equals(category))
                        {
                            this.Elements.add(new ModuleButton(function, this));
                        }
                    }
                }
            });
            d2 += d0 + 5.0D;
        }

        rpanels = new ArrayList<>();

        rpanels.addAll(panels);

        Collections.reverse(rpanels);
    }

    public void updateScreen()
    {
        for (Panel panel : panels)
        {
            for (ModuleButton modulebutton : panel.Elements)
            {
                for (Element element : modulebutton.menuelements)
                {
                    element.tick();

                    if (!panel.extended)
                    {
                        element.anim = 0.0F;
                        element.anim2 = 0.0F;
                    }

                    if (!modulebutton.extended)
                    {
                        element.anim = 0.0F;
                        element.anim2 = 0.0F;
                    }
                }
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        final ScaledResolution sr = new ScaledResolution(mc);
        RenderUtils.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 1342177280);

        for (Panel panel : panels)
        {
            panel.drawScreen(mouseX, mouseY, partialTicks);
        }

        this.mb = null;
        label126:

        for (Panel panel1 : panels)
        {
            if (panel1 != null && panel1.visible && panel1.extended && panel1.Elements != null && panel1.Elements.size() > 0)
            {
                Iterator iterator = panel1.Elements.iterator();
                ModuleButton modulebutton;

                do {
                    if (!iterator.hasNext()) {
                        continue label126;
                    }

                    modulebutton = (ModuleButton) iterator.next();

                } while (!modulebutton.listening);

                this.mb = modulebutton;
                break;
            }
        }

        for (Panel panel2 : panels)
        {
            if (panel2.extended && panel2.visible && panel2.Elements != null)
            {
                for (ModuleButton modulebutton1 : panel2.Elements)
                {
                    if (modulebutton1.extended && modulebutton1.menuelements != null && !modulebutton1.menuelements.isEmpty())
                    {
                        double d0 = 0.0D;

                        for (Element element : modulebutton1.menuelements)
                        {
                            element.offset = d0;
                            element.update();
                            element.drawScreen(mouseX, mouseY, partialTicks);
                            d0 += element.height;
                        }
                    }
                }
            }
        }

        if (this.mb != null)
        {
            ScaledResolution scaledresolution = new ScaledResolution(mc);
            drawRect(0, 0, this.width, this.height, -872415232);
            GL11.glPushMatrix();
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Bind Manager", (float)(scaledresolution.getScaledWidth() / 2), (float)(scaledresolution.getScaledHeight() / 2 - 30), -15558688);
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Press any key...", (float)(scaledresolution.getScaledWidth() / 2), (float)(scaledresolution.getScaledHeight() / 2 - 10), -1);
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("'Escape' - unbound", (float)(scaledresolution.getScaledWidth() / 2), (float)(scaledresolution.getScaledHeight() / 2), -1);
            GL11.glPopMatrix();
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (this.mb == null)
        {
            for (Panel panel : rpanels)
            {
                if (panel.extended && panel.visible && panel.Elements != null)
                {
                    label68:

                    for (ModuleButton modulebutton : panel.Elements)
                    {
                        if (modulebutton.extended)
                        {
                            Iterator iterator = modulebutton.menuelements.iterator();

                            while (true)
                            {
                                if (!iterator.hasNext())
                                {
                                    continue label68;
                                }

                                Element element = (Element)iterator.next();

                                if (element.mouseClicked(mouseX, mouseY, mouseButton))
                                {
                                    break;
                                }
                            }

                            return;
                        }
                    }
                }
            }

            for (Panel panel1 : rpanels)
            {
                if (panel1.mouseClicked(mouseX, mouseY, mouseButton))
                {
                    return;
                }
            }

            super.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state)
    {
        if (this.mb == null)
        {
            for (Panel panel : rpanels)
            {
                if (panel.extended && panel.visible && panel.Elements != null)
                {
                    for (ModuleButton modulebutton : panel.Elements)
                    {
                        if (modulebutton.extended)
                        {
                            for (Element element : modulebutton.menuelements)
                            {
                                element.mouseReleased(mouseX, mouseY, state);
                            }
                        }
                    }
                }
            }

            for (Panel panel1 : rpanels)
            {
                panel1.mouseReleased(mouseX, mouseY, state);
            }

            super.mouseReleased(mouseX, mouseY, state);
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        for (Panel panel : rpanels)
        {
            if (panel != null && panel.visible && panel.extended && panel.Elements != null && panel.Elements.size() > 0)
            {
                for (ModuleButton modulebutton : panel.Elements)
                {
                    try
                    {
                        if (modulebutton.keyTyped(typedChar, keyCode))
                        {
                            return;
                        }
                    }
                    catch (IOException ioexception1)
                    {
                        ioexception1.printStackTrace();
                    }
                }
            }
        }

        super.keyTyped(typedChar, keyCode);
    }

    public void initGui()
    {
        for (Panel panel : panels)
        {
            for (ModuleButton modulebutton : panel.Elements)
            {
                for (Element element : modulebutton.menuelements)
                {
                    element.anim = 0.0F;
                    element.anim2 = 0.0F;
                }
            }
        }
    }

    public void onGuiClosed()
    {
        for (Panel panel : rpanels)
        {
            if (panel.extended && panel.visible && panel.Elements != null)
            {
                for (ModuleButton modulebutton : panel.Elements)
                {
                    if (modulebutton.extended)
                    {
                        for (Element element : modulebutton.menuelements)
                        {
                            if (element instanceof ElementSlider)
                            {
                                ((ElementSlider)element).dragging = false;
                            }
                        }
                    }
                }
            }
        }
    }
}
