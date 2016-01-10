package binnie.craftgui.events;

import binnie.craftgui.core.IWidget;
import binnie.craftgui.events.Event;
import binnie.craftgui.events.EventHandler;

public class EventButtonClicked extends Event {
   public EventButtonClicked(IWidget origin) {
      super(origin);
   }

   public abstract static class Handler extends EventHandler {
      public Handler() {
         super(EventButtonClicked.class);
      }
   }
}
