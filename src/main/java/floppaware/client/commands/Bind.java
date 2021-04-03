package floppaware.client.commands;

import floppaware.api.chat.Chat;
import floppaware.api.command.Command;
import floppaware.client.Floppa;
import floppaware.client.utils.FileManager;
import net.minecraft.client.util.InputUtil;

public class Bind extends Command
{
  public Bind()
  {
    super("bind", "binds mods through a command");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    Floppa.modules.getModuleByName(args[0]).setBind(InputUtil.fromTranslationKey("key.keyboard." + args[1].toLowerCase()).getCode());
    Chat.info(args[0] + " bound to " + args[1]);
    FileManager.INSTANCE.saveBinds();
  }
}
