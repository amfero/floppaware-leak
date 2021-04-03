package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.CheckBoxSetting;

public class AutoSprint extends Module
{
	
	CheckBoxSetting rage = new CheckBoxSetting("Rage", this, false);
	
  public AutoSprint()
  {
    super("AutoSprint", "Makes you sprint without pressing your sprint button", -1, Category.Movement, true);
    addSetting(rage);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
	  if (rage.get()) {
		  mc.player.setSprinting(true);
	  }
	  else {
		  if(mc.options.keyForward.isPressed()) mc.player.setSprinting(true);
	  }
  }
}
