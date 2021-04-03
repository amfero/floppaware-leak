package floppaware.client.commands;

import floppaware.api.command.Command;
import floppaware.client.Floppa;

public class Shrug extends Command
{
  public Shrug()
  {
    super("shrug", "\u00af\\_(\u30b7)_/\u00af");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    Floppa.MC.player.sendChatMessage("\u00af\\_(\u30b7)_/\u00af");
  }
}
