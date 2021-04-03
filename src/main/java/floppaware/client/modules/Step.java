package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.SliderSetting;

public class Step extends Module
{
  SliderSetting height = new SliderSetting("Step Height", this, 2, 0, 6, true);

  public Step()
  {
    super("Step", "Increases step height", -1, Category.Movement, true);
    addSetting(height);
  }

  public void
  onDisable()
  {
    super.onDisable();
    mc.player.stepHeight = 0.6f;
  }

  @PogEvent
  public void
  onTick(TickEvent event) {

    if (mc.player == null || mc.world == null)
      return;
    mc.player.stepHeight = (float) height.get();
  }
}
