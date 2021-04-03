package floppaware.api.setting;

import floppaware.api.module.Module;

public class ModeSetting<T extends Enum<?>> extends Setting
{
  public T[] values;
  private T value;

  public ModeSetting(String name, Module featureList, T current, T[] values)
  {
    super(name, featureList, Type.Mode);
    this.value = current;
    this.values = values;
  }

  public void
  set(T newVal)
  {
    this.value = newVal;
  }

  public T
  get()
  {
    return this.value;
  }

  public T
  getNext()
  {
    int n = value.ordinal() + 1;
    if(n >= values.length)
      n = 0;
    return values[n];
  }
}
