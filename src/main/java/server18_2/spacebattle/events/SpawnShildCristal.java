package server18_2.spacebattle.events;

//import com.sun.tools.javac.file.Locations;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import server18_2.spacebattle.Main;

import java.util.ArrayList;

public class SpawnShildCristal implements Listener {

    @EventHandler
    public void PlayerInteractEvent (PlayerMoveEvent event) {
        Location location = event.getPlayer().getFlooredLocation();
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
}
