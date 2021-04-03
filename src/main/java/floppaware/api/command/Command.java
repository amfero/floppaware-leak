package floppaware.api.command;

import floppaware.client.Floppa;

public class Command
{
  private final String name;
  private final String desc;

  public Command(String name, String desc)
  {
    this.name = name;
    this.desc = desc;
  }

  public void
  execute(String command, String[] args) throws Exception
  {

  }
  
  public void onTrigger(String arguments) {}

  public String
  getName()
  {
    return this.name;
  }

  public String
  getDesc()
  {
    return this.desc;
  }
}
