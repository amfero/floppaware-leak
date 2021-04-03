package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.SliderSetting;
import net.minecraft.item.BowItem;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class BowSpam extends Module
{
  SliderSetting charge = new SliderSetting("BowSpam Charge", this, 0.5, 0, 1, false);

  public BowSpam()
  {
    super("BowSpam", "bow fast", -1, Category.Combat, true);
    addSetting(charge);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(!(mc.player.getMainHandStack().getItem() instanceof RangedWeaponItem) || !mc.player.isUsingItem())
      return;

    if(charge.get() < BowItem.getPullProgress(mc.player.getItemUseTime()))
    {
      mc.player.stopUsingItem();
      mc.player.networkHandler.sendPacket(new PlayerActionC2SPacket(Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, Direction.UP));
    }
  }

}
