package floppaware.client.modules;

import floppaware.api.module.Category;
import floppaware.api.module.Module;

public class AntiHurtcam extends Module
{
  public AntiHurtcam()
  {
    super("AntiHurtcam", "Stops the hurtcam animation", -1, Category.Render, true);
  }
}
