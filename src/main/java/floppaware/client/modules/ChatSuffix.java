package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.SendPacketEvent;
import floppaware.api.mixin.MixinIChatMessageC2SPacket;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.ModeSetting;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

public class ChatSuffix extends Module
{
  private final String BC_SUFFIX = " \u2595 \uff26\uff4c\uff4f\uff50\uff50\uff41\uff57\uff41\uff52\uff45\u0020\u005b\u0076\u0031\u002e\u0032\u005d\u0020\u2713";
  private final String BCBC_SUFFIX = " | mrnv is godddd";
  ModeSetting<Mode> mode = new ModeSetting<Mode>("ChatSuffix Suffix", this, Mode.Floppware, Mode.values());

  public ChatSuffix()
  {
    super("ChatSuffix", "Adds a Suffix to the chat.", -1, Category.Misc, true);
    addSetting(mode);
  }

  @PogEvent
  public void
  onPacketSend(SendPacketEvent event)
  {
    if(event.packet instanceof ChatMessageC2SPacket)
    {
      MixinIChatMessageC2SPacket m = (MixinIChatMessageC2SPacket) event.packet;
      String message = m.getChatMessage();
      if(message.startsWith("/") || message.startsWith(".") || message.endsWith(BC_SUFFIX) || message.endsWith(BCBC_SUFFIX) || message.startsWith(";"))
        return;

      if(mode.get() == Mode.Floppware)
      {
        m.setChatMessage(message + BC_SUFFIX);
      }
      else if(mode.get() == Mode.mrnv)
      {
        m.setChatMessage(message + BCBC_SUFFIX);
      }
    }
  }

  enum Mode
  {
    Floppware,
    mrnv
  }
}
