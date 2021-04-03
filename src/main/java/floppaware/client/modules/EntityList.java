package floppaware.client.modules;

import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.client.Floppa;

public class EntityList extends Module
{
  public EntityList()
  {
    super("EntityList", "EntityList settings yes", -1, Category.Hud, false);
  }

  public void
  onEnable()
  {
    Floppa.clickGuiManager.pinnableFrameHashMap.get("EntityList").visible = true;
  }

  public void
  onDisable()
  {
    Floppa.clickGuiManager.pinnableFrameHashMap.get("EntityList").visible = false;
  }
}
