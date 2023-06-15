package server18_2.spacebattle;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * klasse zum initiieren und referenzieren der items des plugins
 * @author Roger-15
 */
public class CustomItems {
	
	public static final ItemStack RAW_SHIELD_CRYSTAL = new ItemStack(Material.BLUE_DYE, 1);
	public static final ItemStack REFINED_SHIELD_CRYSTAL = new ItemStack(Material.LAPIS_LAZULI, 1);
	public static final ItemStack CHARGED_SHIELD_CRYSTAL = new ItemStack(Material.LAPIS_LAZULI, 1);
	
	public static final ItemStack[] ITEMS = {RAW_SHIELD_CRYSTAL, REFINED_SHIELD_CRYSTAL, CHARGED_SHIELD_CRYSTAL};
	
	//custom model data sorgt dafür, dass die items später in nem texture pack anders aussehen können als die standarditems in minecraft, noch nicht eingebaut
	public static void init() {
		//raw shield crystal
		ItemMeta rawMeta = RAW_SHIELD_CRYSTAL.getItemMeta();
		rawMeta.setCustomModelData(43201);
		rawMeta.setDisplayName("§bRaw Shield Crystal");
		RAW_SHIELD_CRYSTAL.setItemMeta(rawMeta);
		
		//refined
		ItemMeta refMeta = REFINED_SHIELD_CRYSTAL.getItemMeta();
		refMeta.setCustomModelData(43202);
		refMeta.setDisplayName("§9Refined Shield Crystal");
		REFINED_SHIELD_CRYSTAL.setItemMeta(refMeta);
		
		//charged
		ItemMeta chrMeta = CHARGED_SHIELD_CRYSTAL.getItemMeta();
		chrMeta.setCustomModelData(43203);
		chrMeta.setDisplayName("§5§lCharged Shield Crystal");
		chrMeta.addEnchant(Enchantment.LUCK, 1, true);
		chrMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		CHARGED_SHIELD_CRYSTAL.setItemMeta(chrMeta);
	}
	
}
