package server18_2.spacebattle;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import server18_2.spacebattle.commands.SetShildCristal;
import server18_2.spacebattle.commands.ShieldsCommand;
import server18_2.spacebattle.commands.TeleportPadCommand;
import server18_2.spacebattle.events.SpawnShildCristal;
import server18_2.spacebattle.events.Teleporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {
//push
//pull
	private static Main instance;
	
	//liste von locations, bei denen ein kristall refined werden kann
	private ArrayList<Location> refineryLocations;
	
	//Liste von Spielen
	private ArrayList<SpacebattleGame> gameList;
	
	//Map von Teleportern
	private Map<Location, Location> tpLocations;

	@Override
	public void onEnable() {
		instance = this;
		getCommand("shildcristal").setExecutor(new SetShildCristal());
		getCommand("shields").setExecutor(new ShieldsCommand());
		getCommand("teleportpad").setExecutor(new TeleportPadCommand());
		Bukkit.getPluginManager().registerEvents(new SpawnShildCristal(), this);
		Bukkit.getPluginManager().registerEvents(new Teleporter(), this);
		refineryLocations = new ArrayList<Location>();
		gameList = new ArrayList<SpacebattleGame>();
		tpLocations = new HashMap<>();
		
		//einfach mal Standardmäßig ein Spiel mit 2 Teams hinzufügen, kann man später noch mit commands oder so machen
		gameList.add(new SpacebattleGame());
		
		CustomItems.init();
	}
	
	@Override
	public void onDisable() {
		
	}

	public ArrayList<Location> getRefineryLocations() {
		return refineryLocations;
	}
	
	public ArrayList<SpacebattleGame> getGames() {
		return gameList;
	}

	public static Main getInstance() {
		return instance;
	}

	public Map<Location, Location> getTpLocations() {
		return tpLocations;
	}
}
