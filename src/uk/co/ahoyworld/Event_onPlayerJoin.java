package uk.co.ahoyworld;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Event_onPlayerJoin implements Listener 
{
	private AhoyPurge plugin;
	public Event_onPlayerJoin(AhoyPurge plugin)
	{
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public void onPlayerJoin (PlayerJoinEvent event)
	{
		if (!plugin.hiddenPlayers.isEmpty())
		{
			Player player = event.getPlayer();
			Player superAdmin = Bukkit.getPlayer(plugin.superAdmin);
			
			if (!player.getName().equalsIgnoreCase(plugin.superAdmin))
			{
				for (String hiddenPlayer : plugin.hiddenPlayers)
				{
					player.hidePlayer(Bukkit.getPlayer(hiddenPlayer));
					superAdmin.sendMessage("Player " + hiddenPlayer + " hid themselves again because a new player joined.");
				}
			} else {
				superAdmin.sendMessage("Players " + plugin.hiddenPlayers.toString() + " are hidden.");
			}
		}
	}
}
