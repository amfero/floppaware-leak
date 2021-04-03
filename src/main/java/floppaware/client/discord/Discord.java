package floppaware.client.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;

public class Discord {
	
    private static String discordID = "820637674435379200";
    private static DiscordRichPresence discordRichPresence = new DiscordRichPresence();
    private static DiscordRPC discordRPC = DiscordRPC.INSTANCE;
    
    public static void startRPC() {

        DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        eventHandlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + "var2: " + var2));
        
        discordRPC.Discord_Initialize(discordID, eventHandlers, true, discordID);
        discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        discordRichPresence.details = MinecraftClient.getInstance().getSession().getUsername() + " is flopping";
        discordRichPresence.largeImageKey = "icon";
        discordRichPresence.largeImageText = "thanks mrnv";
        discordRichPresence.state = null;
        discordRPC.Discord_UpdatePresence(discordRichPresence);
        
    }

    public static void stopRPC() {
        discordRPC.Discord_Shutdown();
        discordRPC.Discord_ClearPresence();
    }

}