package floppaware.api.mixin;

import floppaware.api.setting.SliderSetting;
import floppaware.client.Floppa;
import net.minecraft.client.render.RenderTickCounter;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderTickCounter.class)
public abstract class MixinRenderTickCounter
{
  @Shadow
  private float lastFrameDuration;

  @Inject(at = {@At(value = "FIELD", target = "Lnet/minecraft/client/render/RenderTickCounter;prevTimeMillis:J", opcode = Opcodes.PUTFIELD, ordinal = 0)}, method = {"beginRenderTick(J)I"})
  public void
  onBeginRenderTick(long l, CallbackInfoReturnable<Integer> ci)
  {
    if(Floppa.modules.getModuleByName("Timer").isToggled())
      lastFrameDuration *= (float) ((SliderSetting) Floppa.settings.getSettingByName("Timer Speed")).get();
  }
}
