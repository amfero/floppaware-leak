package floppaware.api.setting;

import floppaware.api.module.Module;

public class CheckBoxSetting extends Setting
{
  private boolean value;

  public CheckBoxSetting(String name, Module freecam, boolean isChecked)
  {
    super(name, freecam, Type.CheckBox);
    this.value = isChecked;
  }

  public void
  set(boolean newValue)
  {
    this.value = newValue;
  }

  public boolean
  get()
  {
    return this.value;
  }
}
