package team.swift.event.events.impl;

import team.swift.event.events.Event;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class AttackEvent
implements Event {
    private EntityPlayer playerIn;
    public Entity target;

    public AttackEvent(EntityPlayer playerIn, Entity target) {
        this.playerIn = playerIn;
        this.target = target;
    }

    public EntityPlayer getPlayerIn() {
        return this.playerIn;
    }

    public Entity getTarget() {
        return this.target;
    }
}

