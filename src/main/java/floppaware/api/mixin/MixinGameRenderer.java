package floppaware.api.mixin;

import floppaware.api.events.RenderEvent;
import floppaware.client.Floppa;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer
{
  @Inject(at = @At("HEAD"), method = "renderHand(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/Camera;F)V", cancellable = true)
  private void
  renderHand(MatrixStack m, Camera c, float f, CallbackInfo ci)
  {
    RenderEvent event = new RenderEvent(m, c, f);
    Floppa.eventManager.callEvent(event);
    if(event.isCancelled())
    {
      ci.cancel();
    }
  }

  @Inject(at = @At("HEAD"), method = "bobViewWhenHurt", cancellable = true)
  private void
  bobViewWhenHurt(MatrixStack m, float f, CallbackInfo ci)
  {
    if(Floppa.modules.getModuleByName("AntiHurtcam").isToggled())
      ci.cancel();
  }
}
  ///@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;rayTrace(DFZ)Lnet/minecraft/util/hit/HitResult;"), method = "updateTargetedEntity")
  ///private HitResult
  ///updateTargetedEntityEntityRayTraceProxy(Entity entity, double maxDist, float partialTicks, boolean includeFluids)
  ///{
    ///if(BubbyClient.modules.getModuleByName("Liquids").isToggled())
      ///return entity.rayTrace(maxDist, partialTicks, true);
    ///return entity.rayTrace(maxDist, partialTicks, includeFluids);
  ///}
///}
