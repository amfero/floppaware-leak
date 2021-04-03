package floppaware.client.modules;
import floppaware.api.eventapi.*;
import floppaware.api.events.*;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.ModeSetting;
import floppaware.api.setting.SliderSetting;

public class NoRender extends Module
{

  ModeSetting<Mode> mode = new ModeSetting<Mode>("AntiFire Mode", this, Mode.Render, Mode.values());
  public NoRender()
  {
    super("AntiFire", "Prevents fire", -1, Category.Render, true);
    addSetting(mode);
    addSetting(new ModeSetting<RenderMode>("AntiFire Render", this, RenderMode.Lowered, RenderMode.values()));
    addSetting(new SliderSetting("AntiFire Level", this, 0.5, 0, 1, false));
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mode.get() == Mode.Effect)
      mc.player.extinguish();
  }

  enum Mode
  {
    Render,
    Effect,
  }

  public enum RenderMode
  {
    Lowered,
    None
  }
}
