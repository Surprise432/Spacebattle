package server18_2.spacebattle.events;

//import com.sun.tools.javac.file.Locations;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import server18_2.spacebattle.CustomItems;
import server18_2.spacebattle.Main;
import server18_2.spacebattle.SpacebattleGame;
import server18_2.spacebattle.SpacebattleTeam;

import java.util.ArrayList;

/**
 * Eventklasse, ich glaube es wäre schlau hier alle Events bezüglich des Schildraums zu behandeln, 
 * dann haben wir so ne art ordnung, wie professionell
 * @author Surprise432
 */
public class SpawnShildCristal implements Listener {
	
	@EventHandler 
	public void spawnCrystalNewMethod(PlayerInteractEvent e) {
		//abbrechen, wenn es keine physische Interaktion mit einer Eisendruckplatte ist.
		if (e.getAction() != Action.PHYSICAL || e.getClickedBlock().getType() != Material.HEAVY_WEIGHTED_PRESSURE_PLATE) return;
		
		//alle Teams in allen Spielen durchgehen
		for (int i = 0; i < Main.getInstance().getGames().size(); i++) {
			SpacebattleGame game = Main.getInstance().getGames().get(i);
			for (int j = 0; j < game.teams(); j++) {
				//wenn Orte nah genug aneinander sind und ein neuer kristall erzeugt werden kann
				if (game.getTeam(j).newCrystalLocation != null 
						&& game.getTeam(j).newCrystalLocation.getWorld() == e.getClickedBlock().getWorld() 
						&& game.getTeam(j).newCrystalLocation.distanceSquared(e.getClickedBlock().getLocation()) < 1
						&& game.getTeam(j).canGenerateNewShieldCrystal()) 
				{
					e.getClickedBlock().getWorld().dropItemNaturally(game.getTeam(j).newCrystalLocation, CustomItems.RAW_SHIELD_CRYSTAL);
					//e.getClickedBlock().getWorld().dropItemNaturally(game.getTeam(j).newCrystalLocation, CustomItems.REFINED_SHIELD_CRYSTAL);
					//e.getClickedBlock().getWorld().dropItemNaturally(game.getTeam(j).newCrystalLocation, CustomItems.CHARGED_SHIELD_CRYSTAL);
					game.getTeam(j).shieldEnergy = false;
					game.getTeam(j).existingShieldCrystal = true;
				}
			}
		}
	}
	
	/**
	 * um die Kristalle in die Schilde einzusetzen, muss später noch auf das custom item angepasst werden
	 */
	@EventHandler
	public void insertCrystal(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if (e.getHand() == EquipmentSlot.OFF_HAND) return;
		
		if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()
				.equals(CustomItems.CHARGED_SHIELD_CRYSTAL.getItemMeta().getDisplayName())) return;
		
		boolean success = false;
		//alle Teams in allen Spielen durchgehen
		for (int i = 0; i < Main.getInstance().getGames().size(); i++) {
			SpacebattleGame game = Main.getInstance().getGames().get(i);
			for (int j = 0; j < game.teams(); j++) {
				SpacebattleTeam team = game.getTeam(j);
				
				if (e.getClickedBlock().getLocation().equals(team.frontShieldsLocation) && team.frontShields < 3) {
					team.frontShields++;
					success = true;
					team.existingShieldCrystal = false;
					e.getPlayer().sendMessage("vorderes Schild aufgeladen " + i + "|" + j);
					team.refreshShieldVisuals();
				}
				if (e.getClickedBlock().getLocation().equals(team.leftShieldsLocation) && team.leftShields < 3) {
					team.leftShields++;
					success = true;
					team.existingShieldCrystal = false;
					e.getPlayer().sendMessage("linkes Schild aufgeladen " + i + "|" + j);
					team.refreshShieldVisuals();
				}
				if (e.getClickedBlock().getLocation().equals(team.rightShieldsLocation) && team.rightShields < 3) {
					team.rightShields++;
					success = true;
					team.existingShieldCrystal = false;
					e.getPlayer().sendMessage("rechtes Schild aufgeladen " + i + "|" + j);
					team.refreshShieldVisuals();
				}
				if (e.getClickedBlock().getLocation().equals(team.backShieldsLocation) && team.backShields < 3) {
					team.backShields++;
					success = true;
					team.existingShieldCrystal = false;
					e.getPlayer().sendMessage("hinteres Schild aufgeladen " + i + "|" + j);
					team.refreshShieldVisuals();
				}
			}
		}
		
		//löscht 1 charged shield crystal
		if (success) {
			e.getPlayer().getInventory().removeItem(CustomItems.CHARGED_SHIELD_CRYSTAL);
			//kein inventar öffnen
			e.setCancelled(true);
		}
	}
	
	/**
	 * wird ausgeführt, wenn ein trichter ein item aufsaugt, 
	 * wenn es ein raffinierter kristall ist, wird er zu einem geladenen umgewandelt
	 */
	@EventHandler
	public void chargeCrystal(InventoryPickupItemEvent e) {
		ItemStack itemIn = e.getItem().getItemStack();
		if (!itemIn.getItemMeta().getDisplayName().equals(CustomItems.REFINED_SHIELD_CRYSTAL.getItemMeta().getDisplayName())) return;
		itemIn.setItemMeta(CustomItems.CHARGED_SHIELD_CRYSTAL.getItemMeta());
		//kp ob das nötig ist
		e.getItem().setItemStack(itemIn);
	}
	
	
	/**
	 * wird ausgeführt, wenn ein item eine druckplatte berührt, vielleicht auch anders
	 */
	@EventHandler
	public void refineCrystal(EntityInteractEvent e) {
		if (e.getEntityType() != EntityType.DROPPED_ITEM) return;
		Item item = (Item) e.getEntity();
		if (item.getItemStack().getType() != CustomItems.RAW_SHIELD_CRYSTAL.getType()) return;
		
		for(Location location : Main.getInstance().getRefineryLocations()) {
			if (e.getBlock().getLocation().equals(location)) {
				ItemStack stack = item.getItemStack();
				stack.setType(CustomItems.REFINED_SHIELD_CRYSTAL.getType());
				stack.setItemMeta(CustomItems.REFINED_SHIELD_CRYSTAL.getItemMeta());
				//kp ob das nötig ist
				item.setItemStack(stack);
				//partikel
				item.getLocation().getWorld().spawnParticle(Particle.CLOUD, item.getLocation(), 10);
				return;
			}
		}
	}
	
}
