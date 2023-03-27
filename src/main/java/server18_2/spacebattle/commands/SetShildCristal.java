package server18_2.spacebattle.commands;

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
		if(commandSender instanceof Player) {
			Player player = (Player) commandSender;
			Main.getInstance().getGames().get(0).getTeam(0).newCrystalLocation = player.getTargetBlock(null,10).getLocation();
			commandSender.sendMessage("yes04");
		} else {
			commandSender.sendMessage("du bist kein spieler '_'");
		}

		return false;
	}
}
