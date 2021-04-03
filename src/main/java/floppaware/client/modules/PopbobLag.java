package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.SliderSetting;

public class PopbobLag extends Module
{
  int delay = 0;
  SliderSetting speed = new SliderSetting("popbob speed", this, 10, 0, 120, true);

  public PopbobLag()
  {
    super("PopbobLag", "popbob is fat", -1, Category.Spam, true);
    addSetting(speed);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    delay++;
    if(delay == speed.get() * 20)
    {
      mc.player.sendChatMessage("lag");
      delay = 0;
    }
  }
}
