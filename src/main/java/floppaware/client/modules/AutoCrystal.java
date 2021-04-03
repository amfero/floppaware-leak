package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.RenderEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.CheckBoxSetting;
import floppaware.api.setting.SliderSetting;
import floppaware.client.Floppa;
import floppaware.client.utils.EntityUtils;
import floppaware.client.utils.RenderUtils;
import floppaware.client.utils.WorldUtils;
import com.google.common.collect.Streams;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AutoCrystal extends Module
{
  public Entity target;
  ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
  ArrayList<BlockPos> blocksRange;
  int oldSlot = -1;
  int counter = 0;
  CheckBoxSetting players = new CheckBoxSetting("AutoCrystal Players", this, true);
  CheckBoxSetting mobs = new CheckBoxSetting("AutoCrystal Mobs", this, true);
  CheckBoxSetting animals = new CheckBoxSetting("AutoCrystal Animals", this, true);
  CheckBoxSetting autoSwitch = new CheckBoxSetting("AutoCrystal AutoSwitch", this, true);
  SliderSetting maxSelfDMG = new SliderSetting("AutoCrystal MaxSelfDMG", this, 4, 0, 20, true);
  SliderSetting minEnemyDMG = new SliderSetting("AutoCrystal MinEnemyDMG", this, 6, 0, 20, true);
  SliderSetting facePlace = new SliderSetting("AutoCrystal FacePlace", this, 6, 0, 20, true);
  SliderSetting waitTicks = new SliderSetting("AutoCrystal WaitTicks", this, 5, 0, 20, true);
  SliderSetting red = new SliderSetting("AutoCrystal Red", this, 100, 0, 255, true);
  SliderSetting green = new SliderSetting("AutoCrystal Green", this, 100, 0, 255, true);
  SliderSetting blue = new SliderSetting("AutoCrystal Blue", this, 100, 0, 255, true);
  SliderSetting alpha = new SliderSetting("AutoCrystal Alpha", this, 100, 0, 255, true);
  CheckBoxSetting fill = new CheckBoxSetting("AutoCrystal Fill", this, true);
  CheckBoxSetting BreakCrystals = new CheckBoxSetting("AutoBreakCrystal", this, true);
  private BlockPos pogBlock;

  public AutoCrystal()
  {
    super("AutoCrystal", "Crystal pvp pog", -1, Category.Combat, true);
    addSetting(players);
    addSetting(mobs);
    addSetting(animals);
    addSetting(autoSwitch);
    addSetting(maxSelfDMG);
    addSetting(minEnemyDMG);
    addSetting(facePlace);
    addSetting(waitTicks);
    addSetting(red);
    addSetting(green);
    addSetting(blue);
    addSetting(alpha);
    addSetting(fill);
    addSetting(BreakCrystals);
    //idk why it didn't just let me add to the blocksRange
    ArrayList<BlockPos> blocksRangeAdder = new ArrayList<BlockPos>();
    int range = 4;
    for(int x = range; x >= -range; x--)
    {
      for(int y = range; y >= -range; y--)
      {
        for(int z = range; z >= -range; z--)
        {
          blocksRangeAdder.add(new BlockPos(x, y, z));
        }
      }
    }
    blocksRange = new ArrayList<>(blocksRangeAdder);
  }

  @PogEvent
  public void
  onRender(RenderEvent event)
  {
    RenderUtils.drawBox(pogBlock,
            (float) red.get() / 255,
            (float) green.get() / 255,
            (float) blue.get() / 255,
            (float) alpha.get() / 255,
            fill.get());
  }

  public void
  onDisable()
  {
    super.onDisable();
    if(oldSlot != -1)
      mc.player.inventory.selectedSlot = oldSlot;
    oldSlot = -1;
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    counter++;
    if(counter < waitTicks.get())
      return;
    counter = 0;


    if(BreakCrystals.get())
      Streams.stream(mc.world.getEntities())
              .filter(e -> e instanceof EndCrystalEntity
                      && mc.player.distanceTo(e) < 4)
              .forEach(EntityUtils::attackEntity);


    target = Streams.stream(mc.world.getEntities())
            .filter(e ->
                    (e instanceof PlayerEntity && players.get())
                            || (e instanceof Monster && mobs.get())
                            || (EntityUtils.isAnimal(e) && animals.get()))
            .filter(e -> !(Floppa.friends.isFriend(e.getDisplayName().getString())) && e != mc.player)
            .filter(e -> mc.player.distanceTo(e) < 13)
            .filter(e -> !((LivingEntity) e).isDead())
            .sorted((a, b) -> Float.compare(a.distanceTo(mc.player), b.distanceTo(mc.player)))
            .findFirst()
            .orElse(null);

    if(target == null)
      return;

    blocks.clear();
    blocksRange.parallelStream()
            .filter(pos -> canPlace(pos.add(mc.player.getX(), mc.player.getY(), mc.player.getZ())))
            .forEach(pos -> blocks.add(pos.add(mc.player.getX(), mc.player.getY(), mc.player.getZ())));

    //calculate the block that does the most damage to the target
    pogBlock = blocks.parallelStream()
            .filter(block -> EntityUtils.getCrystalDamage(mc.player, block) < maxSelfDMG.get())
            .filter(block -> {
              LivingEntity livingEntity = (LivingEntity) target;
              if(livingEntity.getHealth() + livingEntity.getAbsorptionAmount() < facePlace.get())
                return true;
              return EntityUtils.getCrystalDamage(livingEntity, block) > minEnemyDMG.get();
            })
            .collect(Collectors.toList())
            .stream()
            .sorted((block1, block2) -> Double.compare(EntityUtils.getCrystalDamage((LivingEntity) target, block2), EntityUtils.getCrystalDamage((LivingEntity) target, block1)))
            .findFirst()
            .orElse(null);

    if(pogBlock == null)
      return;

    if(autoSwitch.get() && mc.player.getOffHandStack().getItem() != Items.END_CRYSTAL)
      oldSlot = EntityUtils.changeHotbarSlotToItem(Items.END_CRYSTAL);

    WorldUtils.placeBlock(new Vec3d(pogBlock.getX(), pogBlock.getY(), pogBlock.getZ()), Hand.OFF_HAND, Direction.UP);

    target = null;


    if(mc.player.getOffHandStack().getItem() == Items.END_CRYSTAL)
      return;
    SwapToCrystal();
    
  }

  private boolean
  canPlace(BlockPos blockPos)
  {
    BlockPos up1 = blockPos.add(0, 1, 0);
    BlockPos up2 = blockPos.add(0, 2, 0);
    return (mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN
            || mc.world.getBlockState(blockPos).getBlock() == Blocks.BEDROCK)
            && mc.world.getBlockState(up1).getBlock() == Blocks.AIR
            && mc.world.getBlockState(up2).getBlock() == Blocks.AIR
            && mc.world.getOtherEntities(null, new Box(up1.getX(), up1.getY(), up1.getZ(), up1.getX() + 1.d, up1.getY() + 1.d, up1.getZ() + 1.d)).isEmpty()
            && mc.world.getOtherEntities(null, new Box(up2.getX(), up2.getY(), up2.getZ(), up2.getX() + 1.d, up2.getY() + 1.d, up2.getZ() + 1.d)).isEmpty();
  }

  private void
  SwapToCrystal()
  {
    for(int i = 0; i < 44; i++)
    {
      if(mc.player.inventory.getStack(i).getItem() == Items.END_CRYSTAL)
      {
        mc.interactionManager.clickSlot(0, i < 9 ? i + 36 : i, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(0, 45, 0, SlotActionType.PICKUP, mc.player);
        mc.interactionManager.clickSlot(0, i < 9 ? i + 36 : i, 0, SlotActionType.PICKUP, mc.player);
        return;
      }
    }
  }
}
