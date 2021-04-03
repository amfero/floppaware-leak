package floppaware.api.events;

import floppaware.api.eventapi.Event;

public class ModuleToggledEvent extends Event
{
  public String testInput;

  public ModuleToggledEvent(String testInput)
  {
    this.testInput = testInput;
  }
}
