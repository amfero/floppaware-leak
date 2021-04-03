package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import floppaware.api.setting.SliderSetting;
import floppaware.client.utils.FileManager;

import java.util.ArrayList;

public class Spammer extends Module
{
  ArrayList<String> spams;
  int index;
  int counter;
  SliderSetting delay = new SliderSetting("Spammer Delay", this, 5, 0, 20, true);

  public Spammer()
  {
    super("Spammer", "Spams stuff from a .txt file in chat. Toggle to refresh", -1, Category.Spam, true);
    addSetting(delay);
  }

  public void
  onEnable()
  {
    super.onEnable();
    spams = FileManager.INSTANCE.getFileContents("Spam.txt");
    index = 0;
  }

  @PogEvent
  public void
  onTick(TickEvent event)
  {
    counter++;
    if(counter >= delay.get() * 20)
    {
      mc.player.sendChatMessage(spams.get(index));
      index++;
      if(index >= spams.size() - 1)
        index = 0;

      counter = 0;
    }
  }
}
