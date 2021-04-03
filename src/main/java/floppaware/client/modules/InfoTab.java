package floppaware.client.modules;

import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.client.Floppa;

public class InfoTab extends Module
{
  public InfoTab()
  {
    super("InfoTab", "InfoTab settings yes", -1, Category.Hud, false);
  }

  public void
  onEnable()
  {
    Floppa.clickGuiManager.pinnableFrameHashMap.get("InfoTab").visible = true;
  }

  public void
  onDisable()
  {
    Floppa.clickGuiManager.pinnableFrameHashMap.get("InfoTab").visible = false;
  }
}
