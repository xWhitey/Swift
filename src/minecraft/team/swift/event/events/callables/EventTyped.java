package team.swift.event.events.callables;

import team.swift.event.events.Event;
import team.swift.event.events.Typed;

public abstract class EventTyped implements Event, Typed {
   private final byte type;

   protected EventTyped(byte eventType) {
      this.type = eventType;
   }

   public byte getType() {
      return this.type;
   }
}
