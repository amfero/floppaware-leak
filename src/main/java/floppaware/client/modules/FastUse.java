package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.mixininterface.MinecraftClientInterface;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.ModeSetting;
import net.minecraft.item.Items;

public class FastUse extends Module
{
  ModeSetting<Mode> mode = new ModeSetting<Mode>("FastUse Mode", this, Mode.All, Mode.values());

  public FastUse()
  {
    super("FastUse", "Use fast", -1, Category.Player, true);
    addSetting(mode);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mode.get() == Mode.EXP && mc.player.getMainHandStack().getItem() != Items.EXPERIENCE_BOTTLE)
      return;
    ((MinecraftClientInterface) mc).setItemUseCooldown(0);
  }

  enum Mode
  {
    All,
    EXP
  }
}
