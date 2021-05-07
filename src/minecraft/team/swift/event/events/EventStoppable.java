package team.swift.event.events;

public abstract class EventStoppable implements Event {
   private boolean stopped;

   public void stop() {
      this.stopped = true;
   }

   public boolean isStopped() {
      return this.stopped;
   }
}
