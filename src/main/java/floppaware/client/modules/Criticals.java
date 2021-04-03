package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.SendPacketEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.CheckBoxSetting;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Criticals extends Module
{
	CheckBoxSetting strict = new CheckBoxSetting("Strict", this, true);

  public Criticals()
  {
    super("Criticals", "Lets you get criticals without jumping", -1, Category.Combat, true);
    addSetting(strict);
  }

  @PogEvent
  public void
  onPacket(SendPacketEvent event)
  {
	  if(event.packet instanceof PlayerInteractEntityC2SPacket)
	    {
	      PlayerInteractEntityC2SPacket packet = (PlayerInteractEntityC2SPacket) event.packet;

	      if(packet.getType() == PlayerInteractEntityC2SPacket.InteractionType.ATTACK)
	      {
	        if(!mc.player.isOnGround())
	          return;
	        if(mc.player.isInLava() || mc.player.isTouchingWater())
	          return;
		        double posX = mc.player.getX();
		        double posY = mc.player.getY();
		        double posZ = mc.player.getZ();
		        if(strict.get()) {
		        	mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX, posY + 0.11, posZ, true));
		            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX, posY + 0.1100013579, posZ, true));
		            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX, posY + 0.1100013579, posZ, true));
			        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX, posY, posZ, false));
		        } else {
			        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX, posY + 0.0625, posZ, true));
			        mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX, posY, posZ, false));
		        }
	      }
	    }
      }
  }
