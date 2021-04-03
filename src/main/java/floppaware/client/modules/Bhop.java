package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.SliderSetting;


public class Bhop extends Module {

    SliderSetting Speed = new SliderSetting("Step Height", this, 2, 1, 10, true);

    public Bhop()
    {
        super("Bhop", "Speed but not epic", -1, Category.Movement, true);
        addSetting(Speed);

    }

    @PogEvent
    public void onTick(TickEvent event) {
        if (mc.player.forwardSpeed > 0 && mc.player.isOnGround()) {
            double speeds = 0.65 + Speed.get() / 30;

            mc.player.jump();
            mc.player.setVelocity(mc.player.getVelocity().x * speeds, 0.255556, mc.player.getVelocity().z * speeds);
            mc.player.sidewaysSpeed += 3.0F;
            mc.player.jump();
            mc.player.setSprinting(true);

            }
        }
    };