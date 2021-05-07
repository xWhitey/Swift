package team.swift.features.module;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import team.swift.event.EventManager;
import team.swift.event.EventTarget;
import team.swift.event.events.Event;
import team.swift.event.events.impl.Render2DEvent;
import team.swift.event.events.impl.UpdateEvent;

import java.lang.annotation.Annotation;

public abstract class Module extends EventManager implements EventTarget, Event {
    private String name;
    private ModuleCategory category;
    private int key;
    public boolean state;
    private long lastEnableTime;
    private long lastDisableTime;

    public ModuleCategory getCategory() {
        return category;
    }

    public Module(String name, ModuleCategory category, int key) {
        //EventManager.register(this);
        this.name = name;
        this.category = category;
        this.key = key;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onRender3D(float partialTicks) {}

    public void toggle() {
        state = !state;
        if (state) {
            EventManager.register(this);
            long j = Minecraft.getSystemTime() - this.lastDisableTime;
            this.lastEnableTime = Minecraft.getSystemTime() - (j < 300L ? 300L - j : 0L);
            onEnable();
        } else {
            EventManager.unregister(this);
            long i = Minecraft.getSystemTime() - this.lastEnableTime;
            this.lastDisableTime = Minecraft.getSystemTime() - (i < 300L ? 300L - i : 0L);
            onDisable();
        }
    }

    public double offset() {
        return Math.abs(MathHelper.clamp(Minecraft.getSystemTime() - (this.state ? this.lastEnableTime : this.lastDisableTime), -300.0F, 300.0F) / 300.0F);
    }

    protected static final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public byte value() {
        return 2;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
