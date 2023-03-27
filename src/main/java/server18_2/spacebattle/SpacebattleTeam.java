package server18_2.spacebattle;

import org.bukkit.Location;

/**
 * Speicher für Daten, die für jedes Team einzeln gespeichert werden sollen
 * @author rk-15
 */
public class SpacebattleTeam {
	
	/**
	 * Energiewert vom Raumschiff
	 */
	int energy;
	
	/**
	 * die Werte der jeweiligen Schilde des Teams. sollte zwischen 0 und 3 sein.
	 */
	int frontShields, leftShields, rightShields, backShields;
	
	/**
	 * die Positionen der Schilde, noch nicht benutzen weil die alle <strong>null</strong> sind.
	 */
	Location frontShieldsLocation, leftShieldsLocation, rightShieldsLocation, backShieldsLocation;
	/**
	 * die Position wo du neue Schildkristalle bekommst
	 */
	public Location newCrystalLocation;
	
	/**
	 * Funktion, die später sagen soll, ob das Team im Moment einen neuen Schildkristall generieren kann, 
	 * oder ob der alte noch im Umlauf ist. 
	 * ich hab das ding jetzt aber prinzipiell auf true gestellt weil ich faul wie scheiße bin und mich darum später kümmern kann.
	 * @return 
	 */
	public boolean canGenerateNewShieldCrystal() {
		//TODO vernünftige Funktion schreiben, sobald der Schildzyklus fertig ist
		return true;
	}
	
}
