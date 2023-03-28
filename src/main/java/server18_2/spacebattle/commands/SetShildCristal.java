package server18_2.spacebattle.commands;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import server18_2.spacebattle.Main;

/**
 * hier kümmern wir uns um den shildcristal command, 
 * auf die neue datenstruktur angepasst aber ich muss das später noch flexibel machen
 * @author Surprise432
 */
public class SetShildCristal implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (strings.length == 0) {
			commandSender.sendMessage("/shildcristal braucht 'set' oder 'reset' als argument mit Index von Spiel und Team dahinter (standard ist 0 0)");
			return false;
		}
		
		/**
		 *./shildcristal reset [spiel] [team] sorgt dafür, dass das ausgewählte team 
		 * des ausgewählten spiels wieder einen kristall generieren kann
		 */
		if (strings.length > 2 && strings[0].equalsIgnoreCase("reset")) {
			//TODO auf exceptions prüfen
			int gameIndex = Integer.parseInt(strings[1]);
			int teamIndex = Integer.parseInt(strings[2]);

			Main.getInstance().getGames().get(gameIndex).getTeam(teamIndex).existingShieldCrystal = false;
			Main.getInstance().getGames().get(gameIndex).getTeam(teamIndex).shieldEnergy = true;
			
			return true;
		}
		
		if(commandSender instanceof Player && strings.length > 2 && strings[0].equalsIgnoreCase("set")) {
			Player player = (Player) commandSender;
			Block targetBlock = player.getTargetBlock(null,10);
			
			//TODO auf exceptions prüfen
			int gameIndex = Integer.parseInt(strings[1]);
			int teamIndex = Integer.parseInt(strings[2]);
			
			Main.getInstance().getGames().get(gameIndex).getTeam(teamIndex).newCrystalLocation = targetBlock.getLocation();
			//nur temporär
			Main.getInstance().getGames().get(gameIndex).getTeam(teamIndex).backShieldsLocation = targetBlock.getLocation();
			commandSender.sendMessage("neue Position für Kristellausgabe von Spiel " + gameIndex + ", Team " + teamIndex + ": " 
					+ targetBlock.getX() + " " + targetBlock.getY() + " " + targetBlock.getZ());
			
			return true;
		}
		commandSender.sendMessage("du hast irgendwie verkackt");

		return false;
	}
}
