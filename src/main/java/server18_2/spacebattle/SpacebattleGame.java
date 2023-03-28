package server18_2.spacebattle;

import java.util.ArrayList;

/**
 * Speicher für Daten, die für jedes Spiel einzeln gespeichert werden sollen. 
 * (Daten liegen auch in den einzelnen <tt>Team</tt>-Objekten)
 * @author Roger-15
 */
public class SpacebattleGame {
	
	ArrayList<SpacebattleTeam> teams;
	
	/**
	 * Erstellt neues <tt>SpacebattleTeam</tt>-Objekt, bei dem man die anzahl der Teams im Spiel festlegt
	 * @param teams  die Anzahl der Teams im Spiel
	 */
	public SpacebattleGame(int teams) {
		this.teams = new ArrayList<SpacebattleTeam>();
		for (int i = 0; i < teams; i++) {
			this.teams.add(new SpacebattleTeam());
		}
	}
	
	/**
	 * Erstellt ein neues Spiel mit 2 Teams. 
	 * Diese Funktion wird ausgeführt, wenn man "new SpacebattleGame();" schreibt
	 */
	public SpacebattleGame() {
		this(2);
	}
	
	public SpacebattleTeam getTeam(int index) {
		return teams.get(index);
	}
	
	public int teams() {
		return teams.size();
	}
	
}
