package floppaware.client;

import floppaware.api.eventapi.EventManager;
import floppaware.api.logging.LoggerProvider;
import floppaware.api.module.ModuleManager;
import floppaware.api.setting.SettingManager;
import floppaware.client.discord.Discord;
import floppaware.client.gui.ClickGuiManager;
import floppaware.client.gui.CommandScreen;
import floppaware.client.utils.FileManager;
import floppaware.client.utils.FriendManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.Logger;

public class Floppa implements ModInitializer
{
  public static final MinecraftClient MC = MinecraftClient.getInstance();
  public static final String VERSION;
  public static EventManager eventManager;
  public static String NAME = "floppaware";
  private static final Logger LOGGER = LoggerProvider.logger();
  public static ClickGuiManager clickGuiManager;
  public static CommandScreen commandScreen;
  public static SettingManager settings;
  public static ModuleManager modules;
  public static FriendManager friends;

  static
  {
    VERSION = FabricLoader.INSTANCE.getMods().stream()
            .filter(mod -> mod.getInfo().getId().equals("floppaware"))
            .findFirst()
            .get()
            .getInfo().getVersion().getFriendlyString();
  }

  @Override
  public void onInitialize()
  {
    LOGGER.info("Initializing " + NAME + " Version " + VERSION);

    eventManager = new EventManager();
    friends = new FriendManager();
    settings = new SettingManager();
    modules = new ModuleManager();
    clickGuiManager = new ClickGuiManager();
    commandScreen = new CommandScreen();
    FileManager.INSTANCE.loadModules();
    FileManager.INSTANCE.loadSettingsList();
    FileManager.INSTANCE.loadGuiPos();
    FileManager.INSTANCE.loadBinds();
    FileManager.INSTANCE.loadFriends();
    if(Floppa.modules.getModuleByName("DiscordRPC").isToggled()) {
    	Discord.startRPC();
    }

    LOGGER.info("Ready to destroy servers!");
  }
}
