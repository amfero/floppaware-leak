package floppaware.client.commands;

import floppaware.api.chat.Chat;
import floppaware.api.command.Command;
import floppaware.api.command.CommandManager;

public class Help extends Command
{
  public Help()
  {
    super("help", "Displays all commands");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    for(Command c : CommandManager.commands)
      Chat.message(c.getName() + " | " + c.getDesc());
  }
}
