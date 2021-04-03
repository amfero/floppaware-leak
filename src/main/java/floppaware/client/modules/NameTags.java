package floppaware.client.modules;

import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.CheckBoxSetting;
import floppaware.api.setting.SliderSetting;

public class NameTags extends Module
{
  public NameTags()
  {
    super("NameTags", "Better nametags", -1, Category.Render, true);
    addSetting(new CheckBoxSetting("NameTags Render Armour", this, true));
    addSetting(new SliderSetting("NameTags Scale", this, 1, 0, 10, false));
  }
}
