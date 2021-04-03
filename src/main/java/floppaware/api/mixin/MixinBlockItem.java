package floppaware.api.mixin;

import floppaware.api.events.PlaceBlockEvent;
import floppaware.client.Floppa;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class MixinBlockItem
{
  @Inject(method = "place(Lnet/minecraft/item/ItemPlacementContext;Lnet/minecraft/block/BlockState;)Z", at = @At("HEAD"))
  private void
  place(ItemPlacementContext itemPlacementContext, BlockState blockState, CallbackInfoReturnable<Boolean> ci)
  {
    PlaceBlockEvent event = new PlaceBlockEvent(itemPlacementContext.getBlockPos(), blockState.getBlock());
    Floppa.eventManager.callEvent(event);
  }
}
