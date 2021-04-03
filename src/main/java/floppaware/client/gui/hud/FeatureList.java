package floppaware.client.gui.hud;
import floppaware.api.setting.ModeSetting;
import floppaware.api.setting.CheckBoxSetting;
import floppaware.client.Floppa;
import static floppaware.client.modules.FeatureList.Mode;
import java.util.ArrayList;

public class FeatureList extends PinnableFrame
{
  public FeatureList()
  {
    super("FeatureList", new ArrayList<>(), new ArrayList<>(), 50);
  }

  public void
  updateFrame()
  {
    this.leftText.clear();
    this.rightText.clear();
    if (((CheckBoxSetting) Floppa.settings.getSettingByName("ForgeHax")).get()) {
	    if(((ModeSetting) Floppa.settings.getSettingByName("FeatureList Side")).get() == Mode.Left)
	      Floppa.modules.getToggledModules().forEach(m -> this.leftText.add(">" + m.getName()));
	    else
	      Floppa.modules.getToggledModules().forEach(m -> this.rightText.add(m.getName() + "<"));
    } else {
	    if(((ModeSetting) Floppa.settings.getSettingByName("FeatureList Side")).get() == Mode.Left)
		      Floppa.modules.getToggledModules().forEach(m -> this.leftText.add(m.getName()));
		else
		      Floppa.modules.getToggledModules().forEach(m -> this.rightText.add(m.getName()));
    }
  }
}
