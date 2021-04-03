package floppaware.client.commands;

import floppaware.api.chat.Chat;
import floppaware.api.command.Command;
import floppaware.client.Floppa;

public class Pitch extends Command
{
  public Pitch()
  {
    super("pitch", "Sets your player's pitch");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    Floppa.MC.player.pitch = Integer.parseInt(args[0]);
    Chat.info("Pitch set to: " + args[0]);
  }
}
