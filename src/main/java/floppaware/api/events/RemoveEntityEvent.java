package floppaware.api.events;

import floppaware.api.eventapi.Event;
import net.minecraft.entity.Entity;

public class RemoveEntityEvent extends Event
{
  public Entity entity;

  public RemoveEntityEvent(Entity e)
  {
    this.entity = e;
  }
}
