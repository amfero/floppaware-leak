package floppaware.api.events;

import floppaware.api.eventapi.Event;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

public class PlaceBlockEvent extends Event
{
  public BlockPos blockPos;
  public Block block;

  public PlaceBlockEvent(BlockPos blockPos, Block block)
  {
    this.blockPos = blockPos;
    this.block = block;
  }
}
