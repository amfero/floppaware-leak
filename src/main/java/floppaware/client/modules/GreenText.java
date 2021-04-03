package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.SendPacketEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

public class GreenText extends Module
{
  public GreenText()
  {
    super("GreenText", "text green", -1, Category.Misc, true);
  }

  @PogEvent
  public void
  onSendPacket(SendPacketEvent event)
  {
    if(event.packet instanceof ChatMessageC2SPacket)
    {
      ChatMessageC2SPacket pack = (ChatMessageC2SPacket) event.packet;
      if(!pack.getChatMessage().startsWith(">") && !pack.getChatMessage().startsWith(".") && !pack.getChatMessage().startsWith("/") && !pack.getChatMessage().startsWith(";"))
      {
        mc.player.sendChatMessage(">" + pack.getChatMessage());
        event.setCancelled(true);
      }
    }
  }
}
