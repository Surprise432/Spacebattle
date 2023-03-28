package server18_2.spacebattle;

import org.bukkit.Location;

/**
 * Speicher für Daten, die für jedes Team einzeln gespeichert werden sollen
 * @author rk-15
 */
public class SpacebattleTeam {
	
	/**
	 * Energiewert von den Schilden
	 */
	public boolean shieldEnergy;
	
	/**
	 * die Werte der jeweiligen Schilde des Teams. sollte zwischen 0 und 3 sein.
	 */
	public int frontShields, leftShields, rightShields, backShields;
	
	/**
	 * die Positionen der Schilde, noch nicht benutzen weil die alle <strong>null</strong> sind.
	 */
	public Location frontShieldsLocation, leftShieldsLocation, rightShieldsLocation, backShieldsLocation;
	/**
	 * die Position wo du neue Schildkristalle bekommst
	 */
	public Location newCrystalLocation;
	
	/**
	 * ob schon ein Kristall existiert
	 */
	public boolean existingShieldCrystal;
	
	/**
	 * Funktion, die später sagen soll, ob das Team im Moment einen neuen Schildkristall generieren kann, 
	 * oder ob der alte noch im Umlauf ist. 
	 * @return 
	 */
	public boolean canGenerateNewShieldCrystal() {
		if (shieldEnergy && !existingShieldCrystal) return true;
		return false;
	}
	
}
