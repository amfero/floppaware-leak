package floppaware.client.gui.components;

import floppaware.api.setting.CheckBoxSetting;
import floppaware.api.setting.Setting;
import floppaware.client.utils.FileManager;
import floppaware.client.utils.RenderUtils2D;
import floppaware.client.utils.Theme;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

import static floppaware.client.Floppa.MC;

public class CheckBox implements Component
{
  private final CheckBoxSetting setting;
  private final Button parent;
  private boolean over;
  private int offset;
  private int x;
  private int y;

  public CheckBox(Setting option, Button button, int offset)
  {
    this.setting = (CheckBoxSetting) option;
    this.parent = button;
    this.x = parent.parent.getX() + parent.parent.getWidth() + 5;
    this.y = parent.parent.getY() + offset;
    this.offset = offset;
  }

  @Override
  public void
  renderComponent(MatrixStack m)
  {
    RenderUtils2D.drawBorderedRect(m,
            this.x - 2,
            parent.parent.getY() + offset - 2,
            this.x + 90, parent.parent.getY() + offset + 14,
            Theme.getInnerColour(), Theme.getBorderColour(), 1);

    MC.textRenderer.drawWithShadow(m,
            this.setting.name.replace(this.parent.mod.getName() + " ", ""),
            this.x + 13,
            parent.parent.getY() + offset + 2, -1);

    RenderUtils2D.drawRectOutline(m,
            this.x + 2, parent.parent.getY() + offset + 2,
            this.x + 10, parent.parent.getY() + offset + 10,
            Theme.getBorderColour(), 1);

    if(this.setting.get())
    {
      Screen.fill(m,
              this.x + 3,
              parent.parent.getY() + offset + 3, this.x + 9,
              parent.parent.getY() + offset + 9, Theme.getExtraColour());
    }
  }

  @Override
  public void
  setOff(int newOff)
  {
    offset = newOff;
  }

  @Override
  public int getHeight()
  {
    return 0;
  }

  @Override
  public void handleMouseInput()
  {

  }

  @Override
  public void
  updateComponent(int mx, int my)
  {
    this.over = isMouseOnButton(mx, my);
    this.x = parent.parent.getX() + parent.parent.getWidth() + 5;
    this.y = parent.parent.getY() + offset;
  }

  @Override
  public void
  mouseClicked(int mx, int my, int mb)
  {
    if(isMouseOnButton(mx, my) && mb == 0 && this.parent.open)
    {
      this.setting.set(!this.setting.get());
      FileManager.INSTANCE.saveSettingsList();
    }
  }

  @Override
  public void mouseReleased(int x, int y, int b)
  {

  }

  @Override
  public int getParentHeight()
  {
    return 0;
  }

  @Override
  public void keyTyped(char c, int k)
  {

  }

  public boolean
  isMouseOnButton(int mx, int my)
  {
    return mx > this.x && mx < this.x + 88 && my > this.y && my < this.y + 12 + 1;

  }
}