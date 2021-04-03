package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall extends Module
{
  public NoFall()
  {
    super("NoFall", "Prevents fall damage", -1, Category.Player, true);
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    if(mc.player.fallDistance >= 2 && !mc.player.isFallFlying())
      mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket(true));
  }
}
