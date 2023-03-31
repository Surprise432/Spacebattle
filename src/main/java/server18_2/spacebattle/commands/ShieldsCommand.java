package server18_2.spacebattle.commands;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import server18_2.spacebattle.Main;
import server18_2.spacebattle.SpacebattleTeam;

/**
 * Command, um Werte der Schilde und deren Positionen zu kontrollieren
 * ./shields get 0 0
 * ./shields set front 0 0
 * ./shields hit front 0 0
 * ./shields addconverter
 * ./shields removeconverter 0
 * 
 * @author Roger-15
 */
public class ShieldsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		//TODO add to wiki
		if (args.length == 1 && args[0].equalsIgnoreCase("addconverter") && sender instanceof Player) {
			Player player = (Player) sender;
			Block targetBlock = player.getTargetBlock(null,10);
			Main.getInstance().getRefineryLocations().add(targetBlock.getLocation());
			return true;
		}
		
		//TODO add to wiki
		if (args.length == 2 && args[0].equalsIgnoreCase("removeconverter")) {
			Main.getInstance().getRefineryLocations().remove(Integer.parseInt(args[1]));
		}
		
		if (args.length < 3) return false;
		
		int gameIndex = Integer.parseInt(args[args.length - 2]);
		int teamIndex = Integer.parseInt(args[args.length - 1]);
		
		if (args.length == 3 && args[0].equalsIgnoreCase("get")) {
			SpacebattleTeam team = Main.getInstance().getGames().get(gameIndex).getTeam(teamIndex);
			Location front = team.frontShieldsLocation;
			Location left = team.leftShieldsLocation;
			Location right = team.rightShieldsLocation;
			Location back = team.backShieldsLocation;
			sender.sendMessage("");
			sender.sendMessage("game: " + gameIndex + ", team: " + teamIndex);
			sender.sendMessage("Position of front shields: " + front.getX() + " " + front.getY() + " " + front.getZ());
			sender.sendMessage("Position of left shields: " + left.getX() + " " + left.getY() + " " + left.getZ());
			sender.sendMessage("Position of right shields: " + right.getX() + " " + right.getY() + " " + right.getZ());
			sender.sendMessage("Position of back shields: " + back.getX() + " " + back.getY() + " " + back.getZ());
			sender.sendMessage("Power of front shields: " + team.frontShields);
			sender.sendMessage("Power of left shields: " + team.leftShields);
			sender.sendMessage("Power of right shields: " + team.rightShields);
			sender.sendMessage("Power of back shields: " + team.backShields);
			sender.sendMessage("existing shield crystal: " + team.existingShieldCrystal);
			sender.sendMessage("hasEnergy: " + team.shieldEnergy);
			return true;
		}
		
		//um die positionen der Schildgeneratoren zu setzen, bei denen man die Kristalle einsetzt
		if (args.length == 4 && args[0].equalsIgnoreCase("set") && sender instanceof Player) {
			Player player = (Player) sender;
			Block targetBlock = player.getTargetBlock(null,10);
			
			if (args[1].equalsIgnoreCase("front")) {
				Main.getInstance().getGames().get(gameIndex).getTeam(teamIndex).frontShieldsLocation = targetBlock.getLocation();
				sender.sendMessage("front shields of game " + gameIndex + ", team " + teamIndex + " set to: "
						+ targetBlock.getX() + " " + targetBlock.getY() + " " + targetBlock.getZ());
			}
			if (args[1].equalsIgnoreCase("left")) {
				Main.getInstance().getGames().get(gameIndex).getTeam(teamIndex).leftShieldsLocation = targetBlock.getLocation();
				sender.sendMessage("left shields of game " + gameIndex + ", team " + teamIndex + " set to: "
						+ targetBlock.getX() + " " + targetBlock.getY() + " " + targetBlock.getZ());
			}
			if (args[1].equalsIgnoreCase("right")) {
				Main.getInstance().getGames().get(gameIndex).getTeam(teamIndex).rightShieldsLocation = targetBlock.getLocation();
				sender.sendMessage("right shields of game " + gameIndex + ", team " + teamIndex + " set to: "
						+ targetBlock.getX() + " " + targetBlock.getY() + " " + targetBlock.getZ());
			}
			if (args[1].equalsIgnoreCase("back")) {
				Main.getInstance().getGames().get(gameIndex).getTeam(teamIndex).backShieldsLocation = targetBlock.getLocation();
				sender.sendMessage("back shields of game " + gameIndex + ", team " + teamIndex + " set to: "
						+ targetBlock.getX() + " " + targetBlock.getY() + " " + targetBlock.getZ());
			}
			
			return true;
		}
		
		if (args.length == 4 && args[0].equalsIgnoreCase("hit")) {
			SpacebattleTeam team = Main.getInstance().getGames().get(gameIndex).getTeam(teamIndex);
			
			if (args[1].equalsIgnoreCase("front") && team.frontShields > 0) {
				team.frontShields--;
			}
			if (args[1].equalsIgnoreCase("left") && team.leftShields > 0) {
				team.leftShields--;
			}
			if (args[1].equalsIgnoreCase("right") && team.rightShields > 0) {
				team.rightShields--;
			}
			if (args[1].equalsIgnoreCase("back") && team.backShields > 0) {
				team.backShields--;
			}
			
			team.refreshShieldVisuals();
			
			return true;
		}
		
		return false;
	}

}
