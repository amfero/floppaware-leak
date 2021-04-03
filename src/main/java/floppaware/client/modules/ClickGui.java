package floppaware.client.modules;

import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.CheckBoxSetting;
import floppaware.api.setting.ModeSetting;
import floppaware.client.Floppa;
import org.lwjgl.glfw.GLFW;

public class ClickGui extends Module
{
  public ClickGui()
  {
    super("ClickGui", "Opens ClickGui", GLFW.GLFW_KEY_RIGHT_SHIFT, Category.Gui, true);
    addSetting(new CheckBoxSetting("ClickGui RenderFrames", this, true));
    addSetting(new ModeSetting<Theme>("ClickGui Theme", this, Theme.floppware, Theme.values()));
  }

  public void
  onEnable()
  {
    mc.openScreen(Floppa.clickGuiManager);
    setToggled(false);
  }

  public void
  onDisable()
  {

  }

  public enum Theme
  {
    Nodus,
    nhack,
    WeepCraft,
    floppware,
  }
}
