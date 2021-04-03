package floppaware.client.commands;

import floppaware.api.command.Command;
import floppaware.client.Floppa;

public class Say extends Command
{
  public Say()
  {
    super("say", "says stuff in chat");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    Floppa.MC.player.sendChatMessage(String.join(" ", args));
  }
}
