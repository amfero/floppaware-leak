package floppaware.client.modules;

import floppaware.api.module.Category;
import floppaware.api.module.Module;

public class AntiChunkBan extends Module
{
  public AntiChunkBan()
  {
    super("AntiChunkBan", "Prevents you from getting chunk banned", -1, Category.Misc, true);
  }
}
