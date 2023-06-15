package server18_2.spacebattle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import server18_2.spacebattle.Main;
import server18_2.spacebattle.SpacebattleGame;

//untested
public class SpacebattleCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length < 1) return false;
		
		if (args.length == 1 && args[0].equalsIgnoreCase("add")) {
			Main.getInstance().getGames().add(new SpacebattleGame());
			return true;
		}
		
		if (args.length == 2 && args[0].equalsIgnoreCase("add")) {
			int teams = 2;
			try {
				teams = Integer.parseInt(args[1]);
			} catch (Exception e) {}
			Main.getInstance().getGames().add(new SpacebattleGame(teams));
			return true;
		}
		
		if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
			int index = -1;
			index = Integer.parseInt(args[1]);
			if (index < 0 || index >= Main.getInstance().getGames().size()) return false;
			Main.getInstance().getGames().remove(index);
			return true;
		}
		
		return false;
	}
	
}
