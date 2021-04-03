package floppaware.client.commands;

//import baritone.api.BaritoneAPI;
import floppaware.api.command.Command;

public class Baritone extends Command
{
  public Baritone()
  {
    super("b", "Baritone API Pog");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    //BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute(String.join(" ", args));
  }
}
