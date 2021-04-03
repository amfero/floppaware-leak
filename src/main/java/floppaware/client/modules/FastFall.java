package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class FastFall extends Module
{
  public FastFall()
  {
    super("FastFall", "Fall fast in h o l e", -1, Category.Movement, true);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.world == null
            || mc.player == null
            || mc.player.isInLava()
            || mc.player.isSubmergedInWater())
      return;

    if(mc.player.isOnGround())
    {
      mc.player.setVelocity(mc.player.getVelocity().x, -1, mc.player.getVelocity().z);
      mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket(true));
    }
  }
}
