package server18_2.spacebattle;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import server18_2.spacebattle.commands.SetShildCristal;
import server18_2.spacebattle.events.SpawnShildCristal;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

    private static Main instance;
    private ArrayList<Location> locationsList;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("shildcristal").setExecutor(new SetShildCristal());
        Bukkit.getPluginManager().registerEvents((Listener) new SpawnShildCristal(), this);
        locationsList = new ArrayList<>();
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ArrayList<Location> getTexturePlateLocations() {
        return locationsList;
    }

    public static Main getInstance() {
        return instance;
    }
}
