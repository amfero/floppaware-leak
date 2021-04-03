package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.SliderSetting;

public class Fly extends Module
{
  SliderSetting speed = new SliderSetting("Fly Speed", this, 1, 0, 5, false);
  boolean oldFlying;

  public Fly()
  {
    super("Fly", "Flight", -1, Category.Movement, true);
    addSetting(speed);
  }

  public void
  onEnable()
  {
    oldFlying = mc.player.abilities.allowFlying;
    super.onEnable();
  }

  public void
  onDisable()
  {
    super.onDisable();
    mc.player.abilities.allowFlying = oldFlying;
    mc.player.abilities.flying = false;
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    mc.player.abilities.setFlySpeed((float) speed.get() / 10);
    mc.player.abilities.allowFlying = true;
    mc.player.abilities.flying = true;
  }
}
