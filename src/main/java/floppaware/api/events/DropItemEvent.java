package floppaware.api.events;

import floppaware.api.eventapi.Event;
import net.minecraft.item.ItemStack;

public class DropItemEvent extends Event
{
  public ItemStack itemStack;

  public DropItemEvent(ItemStack itemStack)
  {
    this.itemStack = itemStack;
  }
}
