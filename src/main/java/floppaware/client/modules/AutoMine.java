package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;

public class AutoMine extends Module
{
  public AutoMine()
  {
    super("AutoMine", "Makes you mine automatically", -1, Category.Player, true);
  }

  public void
  onDisable()
  {
    super.onDisable();
    mc.options.keyAttack.setPressed(false);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(!this.isToggled())
      return;
    mc.options.keyAttack.setPressed(true);
  }
}
