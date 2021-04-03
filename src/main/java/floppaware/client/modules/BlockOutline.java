package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.RenderEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.CheckBoxSetting;
import floppaware.api.setting.SliderSetting;
import floppaware.client.utils.RenderUtils;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

public class BlockOutline extends Module
{
  SliderSetting red = new SliderSetting("BlockOutline Red", this, 255, 0, 255, true);
  SliderSetting green = new SliderSetting("BlockOutline Green", this, 255, 0, 255, true);
  SliderSetting blue = new SliderSetting("BlockOutline Blue", this, 255, 0, 255, true);
  SliderSetting alpha = new SliderSetting("BlockOutline Alpha", this, 255, 0, 255, true);
  CheckBoxSetting fill = new CheckBoxSetting("BlockOutline Fill", this, false);
  private HitResult blockHit;

  public BlockOutline()
  {
    super("BlockOutline", "Outlines the block you are looking at", -1, Category.Render, true);
    addSetting(red);
    addSetting(green);
    addSetting(blue);
    addSetting(alpha);
    addSetting(fill);
  }

  @PogEvent
  public void
  onRender(RenderEvent event)
  {
    blockHit = mc.getCameraEntity().raycast(20.d, 0.f, false);
    if(blockHit.getType() == HitResult.Type.BLOCK)
      RenderUtils.drawBox(((BlockHitResult) blockHit).getBlockPos(),
              (float) red.get() / 255,
              (float) green.get() / 255,
              (float) blue.get() / 255,
              (float) alpha.get() / 255,
              fill.get());
  }
}
