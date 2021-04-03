package floppaware.api.mixin;

import floppaware.api.events.AddEntityEvent;
import floppaware.api.events.RemoveEntityEvent;
import floppaware.client.Floppa;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public abstract class MixinClientWorld
{
  @Inject(at = @At("HEAD"), method = "addEntity", cancellable = true)
  public void
  addEntity(int id, Entity entity, CallbackInfo ci)
  {
    AddEntityEvent event = new AddEntityEvent(entity);
    Floppa.eventManager.callEvent(event);
    if(event.isCancelled())
      ci.cancel();
  }

  @Inject(at = @At("HEAD"), method = "finishRemovingEntity", cancellable = true)
  private void
  finishRemovingEntity(Entity entity, CallbackInfo ci)
  {
    RemoveEntityEvent event = new RemoveEntityEvent(entity);
    Floppa.eventManager.callEvent(event);
    if(event.isCancelled())
      ci.cancel();
  }
}
