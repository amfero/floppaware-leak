package floppaware.client.commands;

import floppaware.api.chat.Chat;
import floppaware.api.command.Command;
import floppaware.api.module.Module;
import floppaware.client.Floppa;

public class Panic extends Command
{
  public Panic()
  {
    super("panic", "disables all modules");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    for(Module m : Floppa.modules.getToggledModules())
      m.setToggled(false);
    Chat.info("All modules disabled");
  }
}
