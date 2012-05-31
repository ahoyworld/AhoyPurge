package uk.co.ahoyworld;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AhoyPurge extends JavaPlugin
{
	Logger log;
			
	public Boolean verbose = false;
	public String superAdmin = "FuzzyBandit";
	public ArrayList<String> hiddenPlayers = new ArrayList<String>();
	
	String pre = ChatColor.GOLD + "[Ahoy-a-Boo]" + ChatColor.WHITE + " ";
	String preAdmin = ChatColor.GOLD + "[Ahoy-a-Boo] " + ChatColor.RED + "[ADMIN]" + ChatColor.WHITE + " ";
	
	public void onEnable()
	{
		new Event_onPlayerJoin(this);
		log = this.getLogger();
		log.info("Plugin has been enabled.");
	}
	
	public void onDisable()
	{
		
		log.info("Plugin has been disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		Player player = (Player) sender;
		String playerName = player.getName();
		if (cmd.getName().equalsIgnoreCase("hide"))
		{
			if (args.length == 1)
			{
				if (args[0].equalsIgnoreCase("verbose") && playerName.equalsIgnoreCase(superAdmin))
				{
					if (!verbose)
					{
						verbose = true;
						Bukkit.getPlayer(superAdmin).sendMessage(preAdmin + "Verbose mode enabled.");
						return true;
					} else {
						verbose = false;
						Bukkit.getPlayer(superAdmin).sendMessage(preAdmin + "Verbose mode disabled.");
						return true;
					}
				} else {
					return false;
				}
			} else {
				if (!hiddenPlayers.contains(playerName))
				{
					for (Player plyr : Bukkit.getOnlinePlayers())
					{
						plyr.hidePlayer(player);
					}
					
					hiddenPlayers.add(playerName);
					player.sendMessage(pre + "You are now hidden from all other players.");
					player.playEffect(player.getLocation(), Effect.POTION_BREAK, 0);
					player.playEffect(player.getLocation(), Effect.EXTINGUISH, 0);
					if (playerName.equalsIgnoreCase(superAdmin)) Bukkit.getPlayer(superAdmin).performCommand("dynmap hide");
					Bukkit.getPlayer(superAdmin).sendMessage(preAdmin + "Player " + player.getName() + " has hidden themselves.");
					
					Bukkit.getPlayer(superAdmin).showPlayer(player);
					
				} else {
					hiddenPlayers.remove(hiddenPlayers.indexOf(playerName));
					for (Player plyr : Bukkit.getOnlinePlayers())
					{
						plyr.showPlayer(player);
					}
					player.sendMessage(pre + "You can now be seen.");
					if (playerName.equalsIgnoreCase(superAdmin)) Bukkit.getPlayer(superAdmin).performCommand("dynmap show");
					Bukkit.getPlayer(superAdmin).sendMessage(preAdmin + "Player " + player.getName() + " has shown themselves again.");
				}
				
				return true;
			}

		}
		
		return false;
	}
}
