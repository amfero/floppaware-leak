package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.util.math.Vec3d;


public class Speed extends Module {

  public Speed()
  {
    super("Speed", "bzzz", -1, Category.Movement, true);
  }


  @PogEvent
  public void onTick(TickEvent event) {
    //System.out.println(mc.player.forwardSpeed + " | " + mc.player.sidewaysSpeed);
    if (mc.options.keySneak.isPressed())
      return;

    /* Strafe */
    if ((mc.player.forwardSpeed != 0 || mc.player.sidewaysSpeed != 0) /*&& mc.player.isOnGround()*/) {
      if (!mc.player.isSprinting()) {
        mc.player.networkHandler.sendPacket(new ClientCommandC2SPacket(mc.player, ClientCommandC2SPacket.Mode.START_SPRINTING));
      }

      mc.player.setVelocity(new Vec3d(0, mc.player.getVelocity().y, 0));
      mc.player.updateVelocity((float) 0.30,
              new Vec3d(mc.player.sidewaysSpeed, 0, mc.player.forwardSpeed));

      double vel = Math.abs(mc.player.getVelocity().getX()) + Math.abs(mc.player.getVelocity().getZ());

      if (vel >= 0.12 && mc.player.isOnGround()) {
        mc.player.updateVelocity(vel >= 0.0 ? 0.0f : 0.0f, new Vec3d(mc.player.sidewaysSpeed, 0, mc.player.forwardSpeed));
        mc.player.jump();
      }
    }
  }};