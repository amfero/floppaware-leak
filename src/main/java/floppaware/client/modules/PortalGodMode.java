package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.SendPacketEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import net.minecraft.network.packet.c2s.play.TeleportConfirmC2SPacket;

public class PortalGodMode extends Module
{
  public PortalGodMode()
  {
    super("PortalGod", "God mode through portals", -1, Category.World, true);
  }

  @PogEvent
  public void
  onPacketSend(SendPacketEvent event)
  {
    if(event.packet instanceof TeleportConfirmC2SPacket)
      event.setCancelled(true);
  }
}
