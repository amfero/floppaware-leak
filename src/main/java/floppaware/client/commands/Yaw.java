package floppaware.client.commands;

import floppaware.api.chat.Chat;
import floppaware.api.command.Command;
import floppaware.client.Floppa;

public class Yaw extends Command
{
  public Yaw()
  {
    super("yaw", "Sets your player's yaw");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    Floppa.MC.player.yaw = Integer.parseInt(args[0]);
    Chat.info("Yaw set to: " + args[0]);
  }
}
