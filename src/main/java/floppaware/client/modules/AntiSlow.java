package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import net.minecraft.entity.effect.StatusEffects;

public class AntiSlow extends Module
{
  public AntiSlow()
  {
    super("AntiSlow", "Stops the slowness effect", -1, Category.Player, true);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.player.hasStatusEffect(StatusEffects.SLOWNESS))
      mc.player.removeStatusEffect(StatusEffects.SLOWNESS);
  }
}
