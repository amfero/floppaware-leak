package floppaware.client.utils;

import floppaware.api.module.Module;
import floppaware.api.setting.*;
import floppaware.client.Floppa;
import floppaware.client.gui.components.Frame;
import floppaware.client.gui.hud.PinnableFrame;
import net.minecraft.client.MinecraftClient;

import java.io.*;
import java.util.ArrayList;

@SuppressWarnings({"rawtypes", "unchecked"})
public enum FileManager
{
  INSTANCE;

  public File BC;
  public File FloppwareClientSettings;

  FileManager()
  {
    this.BC = new File(MinecraftClient.getInstance().runDirectory.getPath() + File.separator + "Floppaware");
    if(!this.BC.exists()) this.BC.mkdirs();

    this.FloppwareClientSettings = new File(MinecraftClient.getInstance().runDirectory.getPath() + File.separator + "Floppaware" + File.separator + "Settings");
    if(!this.FloppwareClientSettings.exists()) this.FloppwareClientSettings.mkdirs();
  }

  public void
  writeToFile(String s, String name)
  {
    try
    {
      File file = new File(this.BC.getAbsolutePath(), name);
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      out.write(s);
      out.close();
    }
    catch(Exception e)
    {
    }
  }

  public void
  saveModules()
  {
    try
    {
      File file = new File(this.BC.getAbsolutePath(), "Modules.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      for(Module m : Floppa.modules.getModules())
      {
        if(m.isToggled() && !m.getName().matches("null") && !m.getName().equals("Freecam"))
        {
          out.write(m.getName());
          out.write("\r\n");
        }
      }
      out.close();
    }
    catch(Exception e)
    {
    }
  }

  public void
  loadModules()
  {
    try
    {
      File file = new File(this.BC.getAbsolutePath(), "Modules.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while((line = br.readLine()) != null)
      {
        for(Module m : Floppa.modules.getModules())
        {
          if(m.getName().equals(line))
          {
            m.setToggled(true);
          }
        }
      }
      br.close();
    }
    catch(Exception e)
    {
      this.saveModules();
    }
  }

  public void
  saveSettingsList()
  {
    try
    {
      File file = new File(FloppwareClientSettings.getAbsolutePath(), "Slider.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      for(Setting i : Floppa.settings.getSettingsByType(Type.Slider))
      {
        SliderSetting slider = (SliderSetting) i;
        out.write(slider.name + ":" + slider.get() + "\r\n");
      }
      out.close();
    }
    catch(Exception e)
    {
    }

    try
    {
      File file = new File(FloppwareClientSettings.getAbsolutePath(), "Check.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      for(Setting i : Floppa.settings.getSettingsByType(Type.CheckBox))
      {
        CheckBoxSetting check = (CheckBoxSetting) i;
        out.write(i.name + ":" + check.get() + "\r\n");
      }
      out.close();
    }
    catch(Exception e)
    {
    }

    try
    {
      File file = new File(FloppwareClientSettings.getAbsolutePath(), "Mode.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      for(Setting i : Floppa.settings.getSettingsByType(Type.Mode))
      {
        ModeSetting mode = (ModeSetting) i;
        out.write(i.name + ":" + mode.get().ordinal() + "\r\n");
      }
      out.close();
    }
    catch(Exception e)
    {
    }
  }

  public void
  loadSettingsList()
  {
    try
    {
      File file = new File(FloppwareClientSettings.getAbsolutePath(), "Slider.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while((line = br.readLine()) != null)
      {
        String curLine = line.trim();
        String name = curLine.split(":")[0];
        String isOn = curLine.split(":")[1];
        for(Setting setting : Floppa.settings.getSettingsByType(Type.Slider))
        {
          if(setting.name.equals(name))
          {
            ((SliderSetting) setting).set(Double.parseDouble(isOn));
          }
        }
      }
      br.close();
    }
    catch(Exception e)
    {
      saveSettingsList();
    }

    try
    {
      File file = new File(FloppwareClientSettings.getAbsolutePath(), "Check.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while((line = br.readLine()) != null)
      {
        String curLine = line.trim();
        String name = curLine.split(":")[0];
        String isOn = curLine.split(":")[1];
        for(Setting setting : Floppa.settings.getSettingsByType(Type.CheckBox))
        {
          if(setting.name.equals(name))
          {
            ((CheckBoxSetting) setting).set(Boolean.parseBoolean(isOn));
          }
        }
      }
      br.close();
    }
    catch(Exception e)
    {
      saveSettingsList();
    }

    try
    {
      File file = new File(FloppwareClientSettings.getAbsolutePath(), "Mode.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while((line = br.readLine()) != null)
      {
        String curLine = line.trim();
        String name = curLine.split(":")[0];
        String isOn = curLine.split(":")[1];
        for(Setting setting : Floppa.settings.getSettingsByType(Type.Mode))
        {
          if(setting.name.equals(name))
          {
            ModeSetting ms = (ModeSetting) setting;
            ms.set(ms.values[Integer.parseInt(isOn)]);
          }
        }
      }
      br.close();
    }
    catch(Exception e)
    {
      saveSettingsList();
    }
  }

  public void
  saveGuiPos()
  {
    try
    {
      File file = new File(BC.getAbsolutePath(), "Gui.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      for(Frame i : Floppa.clickGuiManager.frames)
      {
        out.write(i.category.name() + ":" + i.getX() + ":" + i.getY() + ":" + i.open + "\r\n");
      }
      for(PinnableFrame i : Floppa.clickGuiManager.pinnableframes)
      {
        out.write(i.title + ":" + i.x + ":" + i.y + ":" + i.open + ":" + i.isPinned() + "\r\n");
      }
      out.close();
    }
    catch(Exception e)
    {
    }
  }

  public void
  loadGuiPos()
  {
    try
    {
      File file = new File(BC.getAbsolutePath(), "Gui.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while((line = br.readLine()) != null)
      {
        String curLine = line.trim();
        String name = curLine.split(":")[0];
        String x = curLine.split(":")[1];
        String y = curLine.split(":")[2];
        String open = curLine.split(":")[3];
        String pin = "false";
        try
        {
          pin = curLine.split(":")[4];
        }
        catch(Exception e)
        {
        }
        for(Frame i : Floppa.clickGuiManager.frames)
        {
          if(i.category.name().equals(name))
          {
            i.setX(Integer.parseInt(x));
            i.setY(Integer.parseInt(y));
            i.open = Boolean.parseBoolean(open);
          }
        }
        for(PinnableFrame i : Floppa.clickGuiManager.pinnableframes)
        {
          if(i.title.equals(name))
          {
            i.setX(Integer.parseInt(x));
            i.setY(Integer.parseInt(y));
            i.open = Boolean.parseBoolean(open);
            i.setPinned(Boolean.parseBoolean(pin));
          }
        }
      }
      br.close();
    }
    catch(Exception e)
    {
      saveGuiPos();
    }
  }

  public void
  saveBinds()
  {
    try
    {
      File file = new File(this.BC.getAbsolutePath(), "Binds.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      for(Module m : Floppa.modules.getModules())
      {
        out.write(m.getName() + ":" + m.getBind());
        out.write("\r\n");
      }
      out.close();
    }
    catch(Exception e)
    {
    }
  }

  public void
  loadBinds()
  {
    try
    {
      File file = new File(this.BC.getAbsolutePath(), "Binds.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while((line = br.readLine()) != null)
      {
        String curLine = line.trim();
        String name = curLine.split(":")[0];
        String bind = curLine.split(":")[1];
        for(Module m : Floppa.modules.getModules())
        {
          if(m.getName().equals(name))
          {
            m.setBind(Integer.parseInt(bind));
          }
        }
      }
      br.close();
    }
    catch(Exception e)
    {
      saveSettingsList();
    }
  }

  public void
  saveFriends()
  {
    try
    {
      File file = new File(this.BC.getAbsolutePath(), "Friends.txt");
      BufferedWriter out = new BufferedWriter(new FileWriter(file));
      for(String f : Floppa.friends.getFriends())
      {
        out.write(f);
        out.write("\r\n");
      }
      out.close();
    }
    catch(Exception e)
    {
    }
  }

  public void
  loadFriends()
  {
    try
    {
      File file = new File(this.BC.getAbsolutePath(), "Friends.txt");
      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while((line = br.readLine()) != null)
      {
        Floppa.friends.addFriendNoSave(line);
      }
      br.close();
    }
    catch(Exception e)
    {
      saveFriends();
    }
  }

  public ArrayList<String>
  getFileContents(String fileName)
  {
    ArrayList<String> outArr = new ArrayList<>();
    try
    {
      File file = new File(this.BC.getAbsolutePath(), fileName);
      if(!file.exists())
      {
        file.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write("This retard didn't put anything in the file!\r\n");
        out.close();
      }

      FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      while((line = br.readLine()) != null)
      {
        outArr.add(line);
      }

      br.close();
      return outArr;
    }
    catch(Exception e)
    {

    }
    return outArr;

  }
}
