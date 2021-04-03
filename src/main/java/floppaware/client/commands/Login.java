package floppaware.client.commands;

import floppaware.api.command.Command;
import floppaware.client.utils.LoginManager;

public class Login extends Command
{
	public Login()
	  {
	    super("login", "login in mojang account");
	  }
	
	@Override
	public void
	execute(String command, String[] args) throws Exception
		{
			LoginManager.login(args[0], args[1]);
		}
	
}