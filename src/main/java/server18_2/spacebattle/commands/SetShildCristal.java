package server18_2.spacebattle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import server18_2.spacebattle.Main;

public class SetShildCristal implements CommandExecutor {
    @Override
    public boolean onCommand (CommandSender commandSender, Command command, String s, String[] strings){
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Main.getInstance().getTexturePlateLocations().add(player.getTargetBlock(null,10).getLocation());
            commandSender.sendMessage("yes01");
        }

        return false;
    }
}
