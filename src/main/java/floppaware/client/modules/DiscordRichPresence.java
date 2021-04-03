package floppaware.client.modules;

import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.client.discord.Discord;;

public class DiscordRichPresence extends Module {
	
	  public DiscordRichPresence()
	  {
	    super("DiscordRPC", "Discord rich presence", -1, Category.Misc, false);
	  }
	  
	  public void onEnable() {
		  Discord.startRPC();
	  }
	  
	  public void onDisable() {
		  Discord.stopRPC();
	  }
}

