package server18_2.spacebattle.commands;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import server18_2.spacebattle.Main;

/**
 * hier kümmern wir uns um den shildcristal command. 
 * 
 * wie du ihn benutzt: /shildcristal set [spiel id] [team id]. 
 * dann wird der angeschaute block (muss eisendruckplatte sein) zum neuen schildkristallspender für das jeweilige team. 
 * standardmäßig /shildcristal set 0 0
 * 
 * alternative funktion: /shildcristal reset [spiel] [team] um dafür zu sorgen dass das team wieder einen generieren kann. 
 * (im normalfall passiert das wenn wir energie haben und der alte benutzt wurde)
 * 
 * @author Surprise432
 * @author Roger-15
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
			int gameIndex = Integer.parseInt(strings[1]);
			int teamIndex = Integer.parseInt(strings[2]);

			Main.getInstance().getGames().get(gameIndex).getTeam(teamIndex).existingShieldCrystal = false;
			Main.getInstance().getGames().get(gameIndex).getTeam(teamIndex).shieldEnergy = true;
			
			return true;
		}
		
		if(commandSender instanceof Player && strings.length > 2 && strings[0].equalsIgnoreCase("set")) {
			Player player = (Player) commandSender;
			Block targetBlock = player.getTargetBlock(null,10);
			
			int gameIndex = Integer.parseInt(strings[1]);
			int teamIndex = Integer.parseInt(strings[2]);
			
			Main.getInstance().getGames().get(gameIndex).getTeam(teamIndex).newCrystalLocation = targetBlock.getLocation();
			//nur temporär
			commandSender.sendMessage("neue Position für Kristellausgabe von Spiel " + gameIndex + ", Team " + teamIndex + ": " 
					+ targetBlock.getX() + " " + targetBlock.getY() + " " + targetBlock.getZ());
			
			return true;
		}
		commandSender.sendMessage("du hast irgendwie verkackt");

		return false;
	}
}
