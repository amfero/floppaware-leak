package floppaware.client.modules;

import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.client.Floppa;

public class PositionTab extends Module
{
  public PositionTab()
  {
    super("PositionTab", "PositionTab settings yes", -1, Category.Hud, false);
  }

  public void
  onEnable()
  {
    Floppa.clickGuiManager.pinnableFrameHashMap.get("PositionTab").visible = true;
  }

  public void
  onDisable()
  {
    Floppa.clickGuiManager.pinnableFrameHashMap.get("PositionTab").visible = false;
  }
}
