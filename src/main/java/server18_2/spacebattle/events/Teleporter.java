package server18_2.spacebattle.events;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import server18_2.spacebattle.Main;

public class Teleporter implements Listener {

	@EventHandler
	public void teleport(PlayerInteractEvent e) {
		if (e.getAction() != Action.PHYSICAL) return;
		
		Location target = Main.getInstance().getTpLocations().get(e.getClickedBlock().getLocation());
		if (target == null) return;
		
		e.getPlayer().teleport(target, TeleportCause.PLUGIN);
	}
	
}
