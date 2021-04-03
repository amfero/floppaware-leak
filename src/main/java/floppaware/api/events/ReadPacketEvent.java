package floppaware.api.events;

import floppaware.api.eventapi.Event;
import net.minecraft.network.Packet;

public class ReadPacketEvent extends Event
{
  public Packet<?> packet;

  public ReadPacketEvent(Packet<?> packet)
  {
    this.packet = packet;
  }
}
