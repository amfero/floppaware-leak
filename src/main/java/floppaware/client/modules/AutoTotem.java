package floppaware.client.modules;
import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.ModeSetting;
import floppaware.api.setting.SliderSetting;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class AutoTotem extends Module
{
  SliderSetting swapHealth = new SliderSetting("AutoTotem SwapHealth", this, 16, 0, 20, true);
  ModeSetting<Mode> mode = new ModeSetting<Mode>("AutoTotem Mode", this, Mode.Force, Mode.values());

  public AutoTotem()
  {
    super("AutoTotem", "fast totem", -1, Category.Combat, true);
    addSetting(swapHealth);
    addSetting(mode);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mode.get() == Mode.Health && mc.player.getHealth() > swapHealth.get())
      return;
    if(mc.player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING)
      return;
    swapToTotem();
  }

  private void
  swapToTotem()
  {
    for(int i = 0; i < 44; i++)
    {
      if(mc.player.inventory.getStack(i).getItem() == Items.TOTEM_OF_UNDYING)
      {
        mc.interactionManager.clickSlot(0, i < 9 ? i + 36 : i, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(0, 45, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(0, i < 9 ? i + 36 : i, 0, SlotActionType.PICKUP, mc.player);
        return;
      }
    }
  }

  enum Mode
  {
    Force,
    Health
  }
}
