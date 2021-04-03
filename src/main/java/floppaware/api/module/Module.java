package floppaware.api.module;

import floppaware.api.chat.Chat;
import floppaware.api.setting.Setting;
import floppaware.client.Floppa;
import floppaware.client.utils.FileManager;
import net.minecraft.client.MinecraftClient;

public class Module
{
  private final String name;
  private final String desc;
  private final Category category;
  public boolean visible;
  protected MinecraftClient mc = MinecraftClient.getInstance();
  boolean isEnabled;
  private int bind;

  public Module(String name, String desc, int bind, Category category, boolean visible)
  {
    this.name = name;
    this.desc = desc;
    this.bind = bind;
    this.category = category;
    this.visible = visible;
  }

  public void
  onEnable()
  {
    Floppa.eventManager.register(this);
    try
    {
      if(Floppa.modules.getModuleByName("Toggles").isToggled())
        Chat.message("\u00a7aEnabled \u00a7f" + this.name);
    }
    catch(Exception e)
    {
    }
  }

  public void
  onDisable()
  {
    Floppa.eventManager.unregister(this);
    try
    {
      if(Floppa.modules.getModuleByName("Toggles").isToggled())
        Chat.message("\u00a7cDisabled \u00a7f" + this.name);
    }
    catch(Exception e)
    {
    }
  }

  public void
  toggle()
  {
    this.setToggled(!this.isEnabled);
    FileManager.INSTANCE.saveModules();
  }

  public boolean
  isToggled()
  {
    return isEnabled;
  }

  public void
  setToggled(boolean b)
  {
    this.isEnabled = b;
    if(this.isEnabled)
    {
      this.onEnable();
    }
    else
    {
      this.onDisable();
    }
  }

  public int
  getBind()
  {
    return this.bind;
  }

  public void
  setBind(int bind)
  {
    this.bind = bind;
    FileManager.INSTANCE.saveBinds();
  }

  public String
  getName()
  {
    return this.name;
  }

  public String
  getDescription()
  {
    return this.desc;
  }

  public Category
  getCategory()
  {
    return this.category;
  }

  public void
  addSetting(Setting setting)
  {
    Floppa.settings.addSetting(setting);
  }
}
