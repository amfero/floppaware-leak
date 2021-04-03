package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;

public class AirJump extends Module
{
  public AirJump()
  {
    super("AirJump", "Accurately named jetpack", -1, Category.Movement, true);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.options.keyJump.isPressed())
      mc.player.jump();
  }
}
