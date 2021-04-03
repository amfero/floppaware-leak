package floppaware.client.modules;

import floppaware.api.module.Category;
import floppaware.api.module.Module;

public class Toggles extends Module
{
  public Toggles()
  {
    super("Toggles", "Sends a message in chat whenever you toggle a module", -1, Category.Misc, false);
  }
}
