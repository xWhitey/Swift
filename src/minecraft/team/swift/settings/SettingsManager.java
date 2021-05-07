package team.swift.settings;


import team.swift.features.module.Module;

import java.util.ArrayList;
import java.util.List;

public class SettingsManager
{
    private ArrayList<Setting> settings = new ArrayList<Setting>();

    public void registerSetting(Setting newsetting)
    {
        this.settings.add(newsetting);
    }

    public void addDouble(String name, String fullName, double min, double max, double current, Module parent)
    {
        this.settings.add(new Setting(name, fullName, parent, current, min, max, false));
    }

    public void addDouble(String name, String fullName, double min, double max, double current, Module parent, Setting mainSetting, String currentMode)
    {
        this.settings.add(new Setting(name, fullName, parent, current, min, max, false, mainSetting, currentMode));
    }

    public void addInt(String name, String fullName, int min, int max, int current, Module parent)
    {
        this.settings.add(new Setting(name, fullName, parent, (double)current, (double)min, (double)max, true));
    }

    public void addInt(String name, String fullName, int min, int max, int current, Module parent, Setting mainSetting, String currentMode)
    {
        this.settings.add(new Setting(name, fullName, parent, (double)current, (double)min, (double)max, true, mainSetting, currentMode));
    }

    public void addBoolean(String name, String fullName, boolean current, Module parent)
    {
        this.settings.add(new Setting(name, fullName, parent, current));
    }

    public void addMode(String name, String fullName, String current, Module parent, String... list)
    {
        this.settings.add(new Setting(name, fullName, parent, current, list));
    }

    public ArrayList<Setting> getSettings()
    {
        return this.settings;
    }

    public ArrayList<Setting> getSettingsByMod(Module func) {
        ArrayList<Setting> arraylist = new ArrayList<Setting>();

        for (Setting setting : this.getSettings()) {
            if (setting.getParentMod().equals(func)) {
                arraylist.add(setting);
            }
        }

        if (arraylist.isEmpty()) {
            return null;
        } else {
            return arraylist;
        }
    }

    public Setting get(Module owner, String name) {
        List<Setting> found = getSettingsByMod(owner);

        if (found == null) return null;

        return found.stream().filter(val -> name.equalsIgnoreCase(val.getName())).findFirst().orElse(null);
    }

    public Setting getSettingByName(String name)
    {
        for (Setting setting : this.getSettings())
        {
            if (setting.getName().equalsIgnoreCase(name))
            {
                return setting;
            }
        }

        System.out.println("Setting not found! (" + name + ").");
        return null;
    }
}
