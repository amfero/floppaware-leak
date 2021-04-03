package floppaware.api.mixin;

import floppaware.api.events.BreakBlockEvent;
import floppaware.client.Floppa;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class MixinClientPlayerInteractionManager
{
  @Inject(at = @At("HEAD"), method = "breakBlock")
  private void
  breakBlock(BlockPos blockPos, CallbackInfoReturnable<Boolean> ci)
  {
    BreakBlockEvent event = new BreakBlockEvent(blockPos, Floppa.MC.world);
    Floppa.eventManager.callEvent(event);
  }
}
