package floppaware.client.commands;

import floppaware.api.command.Command;
import floppaware.client.Floppa;
import net.minecraft.text.LiteralText;

public class Dupe extends Command
{
  public Dupe()
  {
    super("dupe", "d00ps 1t3m");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    Floppa.MC.player.dropSelectedItem(true);
    Floppa.MC.player.networkHandler.getConnection().disconnect(new LiteralText("d000000p"));
  }
}
