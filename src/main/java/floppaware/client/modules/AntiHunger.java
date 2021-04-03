package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.SendPacketEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;

public class AntiHunger extends Module {

    public AntiHunger() 
    {
      super("AntiHunger", "Prevents hunger", -1, Category.Misc, true);
    }

  @PogEvent
  public void
  onPacketSend(SendPacketEvent event)
  {
    if (((ClientCommandC2SPacket) event.packet).getMode() == ClientCommandC2SPacket.Mode.START_SPRINTING 
        || ((ClientCommandC2SPacket) event.packet).getMode() == ClientCommandC2SPacket.Mode.STOP_SPRINTING)
        event.setCancelled(true);
  }
}
