package floppaware.client.commands;

import floppaware.api.chat.Chat;
import floppaware.api.command.Command;
import floppaware.client.Floppa;

public class Friend extends Command
{
  public Friend()
  {
    super("friend", "manages your friends");
  }

  @Override
  public void
  execute(String command, String[] args) throws Exception
  {
    if(args[0].equalsIgnoreCase("add"))
    {
      Floppa.friends.addFriend(args[1]);
      Chat.info(args[1] + " added to friends");
    }
    else if(args[0].equalsIgnoreCase("remove"))
    {
      Floppa.friends.removeFriend(args[1]);
      Chat.info(args[1] + " removed from friends");
    }
    else if(args[0].equalsIgnoreCase("list"))
    {
      for(String s : Floppa.friends.getFriends())
        Chat.info(s);
    }
  }
}
