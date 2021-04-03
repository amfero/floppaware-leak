package floppaware.client.modules;

import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.client.Floppa;

public class InvPreview extends Module
{
  public InvPreview()
  {
    super("InvPreview", "InvPrewiew settings yes", -1, Category.Hud, false);
  }

  public void
  onEnable()
  {
    Floppa.clickGuiManager.pinnableFrameHashMap.get("InvPreview").visible = true;
  }

  public void
  onDisable()
  {
    Floppa.clickGuiManager.pinnableFrameHashMap.get("InvPreview").visible = false;
  }
}
