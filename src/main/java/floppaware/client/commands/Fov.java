package floppaware.client.commands;

import floppaware.api.chat.Chat;
import floppaware.api.command.Command;
import floppaware.client.Floppa;

public class Fov extends Command
{
    public Fov()
    {
        super("fov", "pro pvp module");
    }

    @Override
    public void
    execute(String command, String[] args) throws Exception
    {
        Floppa.MC.options.fov = Integer.parseInt(args[0]);
        Chat.info("Fov set to: " + args[0]);
    }
}
