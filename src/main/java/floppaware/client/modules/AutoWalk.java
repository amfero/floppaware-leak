package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;

public class AutoWalk extends Module
{
  public AutoWalk()
  {
    super("AutoWalk", "Makes you walk automatically", -1, Category.Movement, true);
  }

  public void
  onDisable()
  {
    super.onDisable();
    mc.options.keyForward.setPressed(false);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    mc.options.keyForward.setPressed(true);
  }
}
