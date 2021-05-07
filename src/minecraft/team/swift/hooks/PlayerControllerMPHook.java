package team.swift.hooks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import team.swift.event.EventManager;
import team.swift.event.events.impl.AttackEvent;

public class PlayerControllerMPHook extends PlayerControllerMP {
    public PlayerControllerMPHook(Minecraft mcIn, NetHandlerPlayClient netHandler) {
        super(mcIn, netHandler);
    }

    @Override
    public void attackEntity(EntityPlayer playerIn, Entity targetEntity) {
        EventManager.call(new AttackEvent(playerIn, targetEntity));
        super.attackEntity(playerIn, targetEntity);
    }
}
