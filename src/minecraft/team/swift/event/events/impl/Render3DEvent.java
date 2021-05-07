package team.swift.event.events.impl;

import team.swift.event.events.Event;

public class Render3DEvent implements Event {
    private float partialTicks;

    public Render3DEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
