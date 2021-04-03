package floppaware.client.modules;

import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.client.Floppa;

public class Console extends Module
{
  public Console()
  {
    super("Console", "Opens Console", 80, Category.Gui, true);
  }

  public void
  onEnable()
  {
    mc.openScreen(Floppa.commandScreen);
    setToggled(false);
  }

  public void
  onDisable()
  {

  }
}
