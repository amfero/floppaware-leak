package floppaware.client.modules;
import floppaware.api.module.*;
import floppaware.api.module.Module;
import floppaware.api.setting.*;
import floppaware.client.Floppa;

public class FeatureList extends Module
{
  public enum Mode
  {
    Left,
    Right
  }
  
  ModeSetting<Mode> mode = new ModeSetting<Mode>("FeatureList Side", this, Mode.Left, Mode.values());
  CheckBoxSetting forgeHax = new CheckBoxSetting("ForgeHax", this, false);
  
  public FeatureList()
  {
    super("FeatureList", "FeatureList settings yes", -1, Category.Hud, false);
    addSetting(mode);
    addSetting(forgeHax);
  }

  public void
  onEnable()
  {
    Floppa.clickGuiManager.pinnableFrameHashMap.get("FeatureList").visible = true;
  }

  public void
  onDisable()
  {
    Floppa.clickGuiManager.pinnableFrameHashMap.get("FeatureList").visible = false;
  }
}
