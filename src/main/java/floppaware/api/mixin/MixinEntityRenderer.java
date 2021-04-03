package floppaware.api.mixin;

import floppaware.api.setting.SliderSetting;
import floppaware.client.Floppa;
import floppaware.client.utils.RenderUtils2D;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.math.Matrix4f;

import java.util.Objects;

import org.lwjgl.system.MathUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer<T extends Entity>
{

  @Shadow
  @Final
  protected EntityRenderDispatcher dispatcher;

  @Inject(at = @At("HEAD"), method = "renderLabelIfPresent", cancellable = true)
  public void renderLabelIfPresent(T entity_1, Text text_1, MatrixStack matrixStack_1, VertexConsumerProvider vertexConsumerProvider_1, int int_1, CallbackInfo info)
  {
    if(Floppa.modules.getModuleByName("NameTags").isToggled())
    {
      if(entity_1 instanceof PlayerEntity)
      {
    	  try {
	        renderTags(entity_1, text_1, matrixStack_1, vertexConsumerProvider_1, int_1);
	        info.cancel();
    	  } catch(NullPointerException e) {}
      }
    }
  }

  private void
  renderTags(T e, Text t, MatrixStack m, VertexConsumerProvider v, int l)
  {
    if(t.getString().contains("Health")) return;
    float f = e.getHeight() + 0.5F;
    int y = "deadmau5".equals(t.getString()) ? -10 : 0;

    m.push();
    m.translate(0.D, f, 0.D);
    m.multiply(this.dispatcher.getRotation());

    float scale = (float) ((SliderSetting) Floppa.settings.getSettingByName("NameTags Scale")).get();
    Camera camera = Floppa.MC.gameRenderer.getCamera();
    double distance = Math.sqrt(camera.getPos().squaredDistanceTo(e.getX(), e.getY(), e.getZ()));
    if(distance > 15)
      scale *= distance / 15;

    m.scale(-0.025F * scale, -0.025F * scale, 0.025F);
    scale += 2;

    Matrix4f m4f = m.peek().getModel();
    PlayerEntity ent = (PlayerEntity) e;

    String str = t.getString();
    MinecraftClient mc = MinecraftClient.getInstance();
    int ping = getPing(ent);
    str = str + " " + getHealthColor(ent) + (int) (ent.getHealth() + ent.getAbsorptionAmount()) + " " + getPingColor(ping) + ping + "ms";

    float x1 = (-mc.textRenderer.getWidth(str) / 2);
    float x2 = x1 + mc.textRenderer.getWidth(str) * 1.5f;

    RenderUtils2D.drawBorderedRect(m, (int) x1 - 2, y - 3 - (int) scale, (int) (x1 + x2) + 2, y + 11 - (int) scale, 0xaa000000, 0xaa3d3d3d, 1);

    mc.textRenderer.draw(str, x1, y - scale, -1, false, m4f, v, true, 0, l);

    m.pop();
  }
  
  private static int getPing(PlayerEntity player) {
      if (MinecraftClient.getInstance().getNetworkHandler() == null) return 0;

      PlayerListEntry playerListEntry = MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(player.getUuid());
      if (playerListEntry == null) return 0;
      return playerListEntry.getLatency();
  }
  
  private String getPingColor(int ping) {
      if (ping < 100) {
          return "\u00a7a";
      } else if (ping < 150) {
          return "\u00a7e";
      } else {
          return "\u00a7c";
      }
  }

  private String getHealthColor(PlayerEntity entity) {
      if (entity.getHealth() + entity.getAbsorptionAmount() >= entity.getMaxHealth() * 0.7) {
          return "\u00a7a";
      } else if (entity.getHealth() + entity.getAbsorptionAmount() >= entity.getMaxHealth() * 0.1) {
          return "\u00a7c";
      } else {
          return "\u00a74";
      }
  }

}
