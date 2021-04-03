package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import net.minecraft.entity.effect.StatusEffects;

public class AntiLevitate extends Module
{
  public AntiLevitate()
  {
    super("AntiLevitate", "Stops the levitation effect", -1, Category.Player, true);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.player.hasStatusEffect(StatusEffects.LEVITATION))
      mc.player.removeStatusEffect(StatusEffects.LEVITATION);
  }
}
