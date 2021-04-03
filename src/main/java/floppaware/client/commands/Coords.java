package floppaware.client.commands;

import floppaware.api.chat.Chat;
import floppaware.api.command.Command;
import floppaware.client.Floppa;

public class Coords extends Command
{
  public Coords()
  {
    super("coords", "copies your coords to your clipboard");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    String coords = "X: " + (int) Floppa.MC.player.getPos().getX() + " Y: " + (int) Floppa.MC.player.getPos().getY() + " Z: " + (int) Floppa.MC.player.getPos().getZ();
    Floppa.MC.keyboard.setClipboard(coords);
    Chat.info("Coords copied to clipboard");
  }
}
