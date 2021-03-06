package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.ReadPacketEvent;
import floppaware.api.events.WalkingUpdateEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.CheckBoxSetting;
import floppaware.api.setting.ModeSetting;
import floppaware.api.setting.SliderSetting;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;

public class Ambiance extends Module
{
  CheckBoxSetting weather = new CheckBoxSetting("Ambiance Weather", this, false);
  ModeSetting<Weather> weatherMode = new ModeSetting<Weather>("Ambiance Type", this, Weather.Clear, Weather.values());
  SliderSetting rain = new SliderSetting("Ambiance RainLevel", this, 0, 0, 2, false);
  SliderSetting time = new SliderSetting("Ambiance Time", this, 12500, 0, 24000, false);

  public Ambiance()
  {
    super("Ambiance", "Forces the time and weather of the world", -1, Category.World, true);
    addSetting(weather);
    addSetting(weatherMode);
    addSetting(rain);
    addSetting(time);
  }

  @PogEvent
  public void
  onMovementUpdate(WalkingUpdateEvent event)
  {
    mc.world.setTimeOfDay((long) time.get());

    if(weather.get())
      if(weatherMode.get() == Weather.Raining)
        mc.world.setRainGradient((float) rain.get());
      else
        mc.world.setRainGradient(0);
  }

  @PogEvent
  public void
  onPacketRead(ReadPacketEvent event)
  {
    if(event.packet instanceof WorldTimeUpdateS2CPacket)
      event.setCancelled(true);
  }

  enum Weather
  {
    Clear,
    Raining
  }
}
