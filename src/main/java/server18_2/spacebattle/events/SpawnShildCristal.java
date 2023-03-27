package server18_2.spacebattle.events;

//import com.sun.tools.javac.file.Locations;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import server18_2.spacebattle.Main;
import server18_2.spacebattle.SpacebattleGame;

import java.util.ArrayList;

/**
 * Eventklasse, ich glaube es wäre schlau hier alle Events bezüglich des Schildraums zu behandeln, 
 * dann haben wir so ne art ordnung, wie professionell
 * @author Surprise432
 */
public class SpawnShildCristal implements Listener {

	/*
	@EventHandler
	public void playerInteractEvent(PlayerMoveEvent event) {
		Location location = event.getPlayer().getLocation();
		
		ArrayList<Location> locs = Main.getInstance().getTexturePlateLocations();
		if(locs.contains(location))
			event.getPlayer().sendMessage("yes");
			World anotherLocWorld = location.getWorld();
			World locWorld = locs.get(0).getWorld();
			if (locWorld != null && locWorld.equals(anotherLocWorld)) {
				// time to launch the player :)
				event.getPlayer().sendMessage("yes2");
				ItemStack cristal = new ItemStack(Material.LAPIS_LAZULI,1);
				locWorld.dropItemNaturally(locs.get(0),cristal);
			}
		
	}
	*/
	
	@EventHandler 
	public void spawnCrystalNewMethod(PlayerInteractEvent e) {
		e.getPlayer().sendMessage(e.getAction().name() + ", " + e.getClickedBlock().getType().name());
		
		//abbrechen, wenn es keine physische Interaktion mit einer Eisendruckplatte ist.
		if (e.getAction() != Action.PHYSICAL || e.getClickedBlock().getType() != Material.HEAVY_WEIGHTED_PRESSURE_PLATE) return;
		
		//alle Teams in allen Spielen durchgehen
		for (int i = 0; i < Main.getInstance().getGames().size(); i++) {
			SpacebattleGame game = Main.getInstance().getGames().get(i);
			for (int j = 0; j < game.teams(); j++) {
				//wenn Orte nah genug aneinander sind
				if (game.getTeam(j).newCrystalLocation != null && game.getTeam(j).newCrystalLocation.getWorld() == e.getClickedBlock().getWorld() && game.getTeam(j).newCrystalLocation.distanceSquared(e.getClickedBlock().getLocation()) < 1) {
					e.getPlayer().sendMessage("yes03");
					ItemStack crystal = new ItemStack(Material.LAPIS_LAZULI,1);
					e.getClickedBlock().getWorld().dropItemNaturally(game.getTeam(j).newCrystalLocation, crystal);
				}
			}
		}
	}
}
