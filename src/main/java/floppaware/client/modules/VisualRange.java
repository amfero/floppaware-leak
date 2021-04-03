package floppaware.client.modules;

import floppaware.api.chat.Chat;
import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.AddEntityEvent;
import floppaware.api.events.RemoveEntityEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import net.minecraft.entity.player.PlayerEntity;

public class VisualRange extends Module
{
  public VisualRange()
  {
    super("VisualRange", "Sends a notification whenever a player enters or exits your visual range", -1, Category.World, true);
  }

  @PogEvent
  public void
  onEntityAdd(AddEntityEvent event)
  {
    if(event.entity instanceof PlayerEntity)
      Chat.message(event.entity.getDisplayName().getString() + " entered visual range");
  }

  @PogEvent
  public void
  onEntityRemove(RemoveEntityEvent event)
  {
    if(event.entity instanceof PlayerEntity)
      Chat.message(event.entity.getDisplayName().getString() + " left visual range");
  }
}
