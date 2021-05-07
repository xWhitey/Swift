package team.swift.event.events.callables;

import team.swift.event.events.Cancellable;
import team.swift.event.events.Event;

public abstract class EventCancellable implements Event, Cancellable {
   private boolean cancelled;

   public boolean isCancelled() {
      return this.cancelled;
   }

   public void setCancelled(boolean state) {
      this.cancelled = state;
   }
}
