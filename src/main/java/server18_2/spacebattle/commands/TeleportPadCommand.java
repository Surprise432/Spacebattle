package server18_2.spacebattle.commands;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import server18_2.spacebattle.Main;

/**
 * ./tpp command ist zum managen der teleportationsfunktion
 * @author Roger-15
 */
public class TeleportPadCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player && args.length >= 4 && args[0].equalsIgnoreCase("add")) {
			Block targetBlock = ((Player) sender).getTargetBlock(null,10);
			double targetX = Integer.parseInt(args[1] + 0.5);
			double targetY = Integer.parseInt(args[2]);
			double targetZ = Integer.parseInt(args[3] + 0.5);
			
			Main.getInstance().getTpLocations().put(targetBlock.getLocation(), new Location(targetBlock.getLocation().getWorld(), targetX, targetY, targetZ));
			
			return true;
		}
		
		if (sender instanceof Player && args.length >= 1 && args[0].equalsIgnoreCase("remove")) {
			Block targetBlock = ((Player) sender).getTargetBlock(null,10);
			Main.getInstance().getTpLocations().remove(targetBlock.getLocation());
			
			return true;
		}
		
		return false;
	}

}
