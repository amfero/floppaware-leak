package floppaware.client.modules;

import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.SliderSetting;

public class Timer extends Module
{
  public Timer()
  {
    super("Timer", "Change the tick speed", -1, Category.World, true);
    addSetting(new SliderSetting("Timer Speed", this, 1, 0, 10, false));
  }
}
