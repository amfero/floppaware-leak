package floppaware.api.events;

import floppaware.api.eventapi.Event;
import net.minecraft.entity.Entity;

public class AddEntityEvent extends Event
{
  public Entity entity;

  public AddEntityEvent(Entity entity)
  {
    this.entity = entity;
  }
}
