package server18_2.spacebattle;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import server18_2.spacebattle.commands.SetShildCristal;
import server18_2.spacebattle.commands.ShieldsCommand;
import server18_2.spacebattle.events.SpawnShildCristal;

import java.util.ArrayList;

public final class Main extends JavaPlugin {
//push
	private static Main instance;
	private ArrayList<Location> locationsList;
	
	//Liste von Spielen
	private ArrayList<SpacebattleGame> gameList;

	@Override
	public void onEnable() {
		instance = this;
		getCommand("shildcristal").setExecutor(new SetShildCristal());
		getCommand("shields").setExecutor(new ShieldsCommand());
		Bukkit.getPluginManager().registerEvents((Listener) new SpawnShildCristal(), this);
		locationsList = new ArrayList<Location>();
		gameList = new ArrayList<SpacebattleGame>();
		
		//einfach mal Standardmäßig ein Spiel mit 2 Teams hinzufügen, kann man später noch mit commands oder so machen
		gameList.add(new SpacebattleGame());
	}
	
	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	public ArrayList<Location> getTexturePlateLocations() {
		return locationsList;
	}
	
	public ArrayList<SpacebattleGame> getGames() {
		return gameList;
	}

	public static Main getInstance() {
		return instance;
	}
}
