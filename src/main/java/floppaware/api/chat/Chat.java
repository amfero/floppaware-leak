package floppaware.api.chat;

import floppaware.client.Floppa;
import net.minecraft.text.LiteralText;

import static floppaware.client.Floppa.MC;

public final class Chat
{

  public static void message(String message)
  {
    MC.inGameHud.getChatHud().addMessage(new LiteralText("\u00a76\u00a7l\u00a7o[" + Floppa.NAME + "] " + message));
  }

  public static void message(String message, String level)
  {
    message("\u00a7l" + level + ": \u00a7f " + message);
  }

  public static void info(String message)
  {
    message(message, "INFO");
  }

  public static void warn(String message)
  {
    message(message, "WARNING");
  }

  public static void error(String message)
  {
    message(message, "ERROR");
  }

  public static void
  encrypted(String message)
  {
    message(message, "ENCRYPTED");
  }
}
