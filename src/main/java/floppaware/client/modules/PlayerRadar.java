package floppaware.client.modules;

import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.client.Floppa;

public class PlayerRadar extends Module
{
  public PlayerRadar()
  {
    super("PlayerRadar", "PlayerRadar settings yes", -1, Category.Hud, false);
  }

  public void
  onEnable()
  {
    Floppa.clickGuiManager.pinnableFrameHashMap.get("PlayerRadar").visible = true;
  }

  public void
  onDisable()
  {
    Floppa.clickGuiManager.pinnableFrameHashMap.get("PlayerRadar").visible = false;
  }
}
