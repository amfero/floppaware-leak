package floppaware.client.commands;

import floppaware.api.command.Command;
import floppaware.client.Floppa;

public class Toggle extends Command
{
  public Toggle()
  {
    super("toggle", "toggles a mod through command");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    Floppa.modules.getModuleByName(args[0]).toggle();
  }
}
