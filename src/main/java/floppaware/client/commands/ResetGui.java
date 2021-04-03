package floppaware.client.commands;

import floppaware.api.command.Command;
import floppaware.client.Floppa;

public class ResetGui extends Command
{
  public ResetGui()
  {
    super("resetgui", "resets the clickgui");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    Floppa.clickGuiManager.resetGui();
  }
}
