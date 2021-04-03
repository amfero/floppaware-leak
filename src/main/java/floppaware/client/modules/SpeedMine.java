package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.SliderSetting;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class SpeedMine extends Module
{
  SliderSetting level = new SliderSetting("SpeedMine Level", this, 1, 1, 3, true);

  public SpeedMine()
  {
    super("SpeedMine", "Adds haste effect", -1, Category.Player, true);
    addSetting(level);
  }

  public void
  onDisable()
  {
    super.onDisable();
    mc.player.removeStatusEffect(StatusEffects.HASTE);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1, (int) level.get()));
  }
}
