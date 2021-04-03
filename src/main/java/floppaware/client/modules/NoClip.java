package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.WalkingUpdateEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;

public class NoClip extends Module
{
  public NoClip()
  {
    super("NoClip", "no, you cannot clip me blocks", -1, Category.World, true);
  }

  public void
  onDisable()
  {
    super.onDisable();
    mc.player.noClip = false;
    mc.player.setOnGround(true);
  }

  @PogEvent
  public void
  onWalkingUpdate(WalkingUpdateEvent event)
  {
    mc.player.noClip = true;
    mc.player.setOnGround(false);
  }
}
