package floppaware.client.modules;

import floppaware.api.module.Module;
import floppaware.api.module.*;

import net.minecraft.util.math.Vec3d;

public class FakePlayer extends Module {
    private floppaware.client.utils.FakePlayer fake;
    private Vec3d newPos;

    public FakePlayer() {

        super("FakePlayer", "FakePlayer", -1, Category.Misc, true);
    }



    public void
    onEnable()
    {

        // fake.setAbsorptionAmount(69);
        fake = new floppaware.client.utils.FakePlayer();
        fake.copyPositionAndRotation(mc.player);
        fake.setBoundingBox(fake.getBoundingBox().expand(0.1));
        fake.spawn();
        fake.abilities.invulnerable = true;
        //fake.abilities.invulnerable = true;
        super.onEnable();
    }

    public void
    onDisable()
    {
        newPos = mc.player.getPos();
        fake.despawn();
        mc.player.setPos(newPos.x, newPos.y, newPos.z);
        super.onDisable();
    }
}