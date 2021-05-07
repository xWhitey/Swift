package team.swift.event.events.impl;

import team.swift.event.events.Event;
import net.minecraft.network.Packet;

public class SendPacketEvent implements Event {
   private Packet<?> packet;
   public boolean cancel;

   public void cancelEvent() {
      this.cancel = true;
   }

   public SendPacketEvent(Packet<?> packet) {
      this.packet = packet;
   }

   public Packet<?> getPacket() {
      return this.packet;
   }
}


