package floppaware.api.setting;

import floppaware.api.module.Module;

public class Setting
{
  public String name;
  public Module parent;
  public Type type;

  public Setting(String name, Module parent, Type type)
  {
    this.name = name;
    this.parent = parent;
    this.type = type;
  }
}
