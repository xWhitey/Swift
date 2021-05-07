package team.swift.settings;

import team.swift.SwiftMain;
import team.swift.features.module.Module;

/**
* Created by Fals3R 3 years ago
*/
@SuppressWarnings("all")
public class Setting<T> {
    private String display;
    private String name;
    private Module parent;
    private String mode;
    private String pMode;
    private String sval;
    private String[] options;
    private boolean bval;
    private double dval;
    private double min;
    private double max;
    private boolean onlyint = false;
    public boolean extended;
    private boolean extraSetting;
    private String currentMode;
    private Setting mainSetting;

    public Setting(String iDisplay, String iname, Module iparent, String isval, String... ioptions) {
        this.display = iDisplay;
        this.name = iname;
        this.parent = iparent;
        this.sval = isval;
        this.options = ioptions;
        this.mode = "Combo";
        this.extraSetting = false;
        SwiftMain.settingManager.registerSetting(this);
    }

    public Setting(String iDisplay, String iname, Module iparent, boolean ibval)
    {
        this.display = iDisplay;
        this.name = iname;
        this.parent = iparent;
        this.bval = ibval;
        this.mode = "Check";
        this.extraSetting = false;
        SwiftMain.settingManager.registerSetting(this);
    }

    public Setting(String iDisplay, String iname, Module iparent, double idval, double imin, double imax, boolean ionlyint)
    {
        this.display = iDisplay;
        this.name = iname;
        this.pMode = "";
        this.parent = iparent;
        this.dval = idval;
        this.min = imin;
        this.max = imax;
        this.onlyint = ionlyint;
        this.mode = "Slider";
        this.extraSetting = false;
        SwiftMain.settingManager.registerSetting(this);
    }

    public Setting(String iDisplay, String iname, Module iparent, String isval, Setting mainSetting, String currentMode, String... ioptions)
    {
        this.display = iDisplay;
        this.name = iname;
        this.parent = iparent;
        this.sval = isval;
        this.options = ioptions;
        this.mode = "Combo";
        this.extraSetting = true;
        this.currentMode = currentMode;
        this.mainSetting = mainSetting;
        SwiftMain.settingManager.registerSetting(this);
    }

    public Setting(String iDisplay, String iname, Module iparent, boolean ibval, Setting mainSetting, String currentMode)
    {
        this.display = iDisplay;
        this.name = iname;
        this.parent = iparent;
        this.bval = ibval;
        this.mode = "Check";
        this.extraSetting = true;
        this.currentMode = currentMode;
        this.mainSetting = mainSetting;
        SwiftMain.settingManager.registerSetting(this);
    }

    public Setting(String iDisplay, String iname, Module iparent, double idval, double imin, double imax, boolean ionlyint, Setting mainSetting, String currentMode)
    {
        this.display = iDisplay;
        this.name = iname;
        this.pMode = "";
        this.parent = iparent;
        this.dval = idval;
        this.min = imin;
        this.max = imax;
        this.onlyint = ionlyint;
        this.mode = "Slider";
        this.extraSetting = true;
        this.currentMode = currentMode;
        this.mainSetting = mainSetting;
        SwiftMain.settingManager.registerSetting(this);
    }

    public String getSimpleName()
    {
        return this.display;
    }

    public String getName()
    {
        return this.name;
    }

    public Module getParentMod()
    {
        return this.parent;
    }

    public String getValString() {
        return this.sval;
    }

    public void setValString(String in)
    {
        this.sval = in;
    }

    public String[] getOptions()
    {
        return this.options;
    }

    public boolean getValBoolean()
    {
        return this.bval;
    }

    public void setValBoolean(boolean in)
    {
        this.bval = in;
    }

    public double getValDouble() {
        if (this.onlyint) {
            this.dval = ((int)this.dval);
        }

        return this.dval;
    }

    public void setValDouble(double in)
    {
        this.dval = in;
    }

    public double getMin()
    {
        return this.min;
    }

    public double getMax()
    {
        return this.max;
    }

    public String getMode()
    {
        return this.mode;
    }

    public boolean isCombo()
    {
        return this.mode.equalsIgnoreCase("Combo");
    }

    public boolean isCheck()
    {
        return this.mode.equalsIgnoreCase("Check");
    }

    public boolean isSlider()
    {
        return this.mode.equalsIgnoreCase("Slider");
    }

    public String getpMode() {
        return this.pMode;
    }

    public boolean onlyInt()
    {
        return this.onlyint;
    }

    public boolean isExtra()
    {
        return this.extraSetting;
    }

    public Setting getMainSetting()
    {
        return this.mainSetting;
    }

    public boolean shouldRender()
    {
        if (!this.extraSetting)
        {
            return true;
        }
        else
        {
            if (this.mainSetting.isCombo())
            {
                if (this.mainSetting.getValString().equalsIgnoreCase(this.currentMode))
                {
                    return true;
                }
            }
            else if (this.mainSetting.isCheck() && this.mainSetting.getValBoolean())
            {
                return true;
            }

            return false;
        }
    }
}

