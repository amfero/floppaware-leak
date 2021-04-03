package floppaware.api.mixin;

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ChatMessageC2SPacket.class)
public interface MixinIChatMessageC2SPacket
{

  @Accessor
  String getChatMessage();

  @Accessor
  void setChatMessage(String newMessage);

}
