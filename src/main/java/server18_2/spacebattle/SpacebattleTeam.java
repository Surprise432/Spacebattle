package server18_2.spacebattle;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * Speicher für Daten, die für jedes Team einzeln gespeichert werden sollen
 * @author Roger-15
 */
public class SpacebattleTeam {
	
	/**
	 * Position vom Raumschiff des Teams, verändert nichts in der welt, ist aber für die anzeige und das gameplay wichtig.
	 */
	public int spaceShipX, spaceShipY;
	
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
	
	public void refreshShieldVisuals() {
		if (frontShieldsLocation != null) {
			Location frontView = new Location(frontShieldsLocation.getWorld(), frontShieldsLocation.getX(), frontShieldsLocation.getY() + 1, frontShieldsLocation.getZ());
			if (frontShields >= 1) {
				frontView.getBlock().setType(Material.SEA_LANTERN);
			} else {
				frontView.getBlock().setType(Material.CYAN_STAINED_GLASS);
			}
			
			frontView.setY(frontView.getY() + 1);
			if (frontShields >= 2) {
				frontView.getBlock().setType(Material.SEA_LANTERN);
			} else {
				frontView.getBlock().setType(Material.CYAN_STAINED_GLASS);
			}
			
			frontView.setY(frontView.getY() + 1);
			if (frontShields >= 3) {
				frontView.getBlock().setType(Material.SEA_LANTERN);
			} else {
				frontView.getBlock().setType(Material.CYAN_STAINED_GLASS);
			}
		}
		
		if (leftShieldsLocation != null) {
			Location frontView = new Location(leftShieldsLocation.getWorld(), leftShieldsLocation.getX(), leftShieldsLocation.getY() + 1, leftShieldsLocation.getZ());
			if (leftShields >= 1) {
				frontView.getBlock().setType(Material.SEA_LANTERN);
			} else {
				frontView.getBlock().setType(Material.CYAN_STAINED_GLASS);
			}
			
			frontView.setY(frontView.getY() + 1);
			if (leftShields >= 2) {
				frontView.getBlock().setType(Material.SEA_LANTERN);
			} else {
				frontView.getBlock().setType(Material.CYAN_STAINED_GLASS);
			}
			
			frontView.setY(frontView.getY() + 1);
			if (leftShields >= 3) {
				frontView.getBlock().setType(Material.SEA_LANTERN);
			} else {
				frontView.getBlock().setType(Material.CYAN_STAINED_GLASS);
			}
		}
		
		if (rightShieldsLocation != null) {
			Location frontView = new Location(rightShieldsLocation.getWorld(), rightShieldsLocation.getX(), rightShieldsLocation.getY() + 1, rightShieldsLocation.getZ());
			if (rightShields >= 1) {
				frontView.getBlock().setType(Material.SEA_LANTERN);
			} else {
				frontView.getBlock().setType(Material.CYAN_STAINED_GLASS);
			}
			
			frontView.setY(frontView.getY() + 1);
			if (rightShields >= 2) {
				frontView.getBlock().setType(Material.SEA_LANTERN);
			} else {
				frontView.getBlock().setType(Material.CYAN_STAINED_GLASS);
			}
			
			frontView.setY(frontView.getY() + 1);
			if (rightShields >= 3) {
				frontView.getBlock().setType(Material.SEA_LANTERN);
			} else {
				frontView.getBlock().setType(Material.CYAN_STAINED_GLASS);
			}
		}
		
		if (backShieldsLocation != null) {
			Location frontView = new Location(backShieldsLocation.getWorld(), backShieldsLocation.getX(), backShieldsLocation.getY() + 1, backShieldsLocation.getZ());
			if (backShields >= 1) {
				frontView.getBlock().setType(Material.SEA_LANTERN);
			} else {
				frontView.getBlock().setType(Material.CYAN_STAINED_GLASS);
			}
			
			frontView.setY(frontView.getY() + 1);
			if (backShields >= 2) {
				frontView.getBlock().setType(Material.SEA_LANTERN);
			} else {
				frontView.getBlock().setType(Material.CYAN_STAINED_GLASS);
			}
			
			frontView.setY(frontView.getY() + 1);
			if (backShields >= 3) {
				frontView.getBlock().setType(Material.SEA_LANTERN);
			} else {
				frontView.getBlock().setType(Material.CYAN_STAINED_GLASS);
			}
		}
	}
	
}
