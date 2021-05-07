package team.swift.features.module.modules.player;

import team.swift.event.EventTarget;
import team.swift.event.events.impl.SendPacketEvent;
import team.swift.event.events.impl.UpdateEvent;
import team.swift.features.module.Module;
import team.swift.features.module.ModuleCategory;
import net.minecraft.network.play.client.CPacketPlayer;
import org.lwjgl.input.Keyboard;
import team.swift.settings.Setting;

/* Created by xWhitey on 05.05.2021 */
public class NoFall extends Module {
    public Setting<String> mode = new Setting<String>("Mode", "Mode", this, "Packet", "Packet", "GroundSpoof");

    public NoFall() {
        super("NoFall", ModuleCategory.PLAYER, Keyboard.KEY_N);
    }

    @EventTarget
    public void onSendPacket(SendPacketEvent event) {
        if (event.getPacket() instanceof CPacketPlayer) {
            System.out.println(((CPacketPlayer) event.getPacket()).isOnGround());
        }
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if (mc.player.fallDistance > 2) {
            mc.player.connection.sendPacket(new CPacketPlayer(true));
        }
    }
}
