package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import net.minecraft.entity.effect.StatusEffects;

public class AntiBlind extends Module
{
  public AntiBlind()
  {
    super("AntiBlind", "Stops the blindness effect", -1, Category.Render, true);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.player.hasStatusEffect(StatusEffects.BLINDNESS))
      mc.player.removeStatusEffect(StatusEffects.BLINDNESS);
  }
}
