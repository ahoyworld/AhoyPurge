package uk.co.ahoyworld;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AhoyPurge extends JavaPlugin
{
	Logger log;
	
	public void onEnable()
	{
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
		
		if (cmd.getName().equalsIgnoreCase("purge"))
		{
			player.sendMessage("You ran a really good command.");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("blah"))
		{
			return true;
		}
		
		return false;
	}
}
