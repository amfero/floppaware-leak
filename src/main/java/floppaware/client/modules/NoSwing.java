package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.SendPacketEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;

public class NoSwing extends Module
{
  public NoSwing()
  {
    super("NoSwing", "Stops the swinging animation packet being sent to the server", -1, Category.Player, true);
  }

  @PogEvent
  public void
  onPacketSend(SendPacketEvent event)
  {
    if(event.packet instanceof HandSwingC2SPacket)
      event.setCancelled(true);
  }
}
