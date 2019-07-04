package org.stonecipher;

import org.bukkit.GameMode;
import org.bukkit.Material;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import org.stonecipher.slabs.SlabEventHandler;

public class Slabs extends JavaPlugin {

	@Override
	public void onEnable() {
		SlabEventHandler handler = new SlabEventHandler();
		this.getServer().getPluginManager().registerEvents(handler , this);
	}

	@Override
	public void onDisable() {
	
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("slab-version")) {
			sender.sendMessage("Slabs by Chibill");
			sender.sendMessage("Version: "+this.getDescription().getVersion());
		}

		if (cmd.getName().equalsIgnoreCase("slab")) {

			if(!(sender instanceof Player)) {
				sender.sendMessage("This command can only be ran from ingame.");
				return true;
			}

			Player player = (Player) sender;

			ItemStack item = player.getInventory().getItemInMainHand();

			if (isSlab(item.getType())) {
				player.getInventory().remove(item);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName("Upside Down Slab");
				meta.addEnchant(Enchantment.DURABILITY, 1, false);
				item.setItemMeta(meta);
				player.getInventory().addItem(item);
				sender.sendMessage("Your requested slab is now in your hand");
			}else {
				if (player.getGameMode() != GameMode.SURVIVAL) {
					ItemStack item1 = new ItemStack(Material.STONE_SLAB);
					ItemMeta meta = item1.getItemMeta();
					meta.setDisplayName("Upside Down Slab");
					meta.addEnchant(Enchantment.DURABILITY, 1, false);
					item1.setItemMeta(meta);
					player.getInventory().addItem(item1);
					sender.sendMessage("Your requested slab has been added inventory.");
				} else if (sender.hasPermission("Slab.survival")) {
						sender.sendMessage("You must have a slab in your hand to get an Upside Down slab in Survival");
				}
			}

			return true;
		}
		return true;
	}

	public static boolean isSlab(Material mat){
		/*	I know this is a bit much but
		*	1) Slab BlockData was grumpy OR I'm too lazy to figure out.
		*	and
		*	2) Given the list isn't that excessive, its probably fine
		*	OH and not all "SLAB"s in Material actually work.
		 */
		if (mat.equals(Material.ACACIA_SLAB)
				|| mat.equals(Material.BIRCH_SLAB)
				|| mat.equals(Material.BRICK_SLAB)
				|| mat.equals(Material.COBBLESTONE_SLAB)
				|| mat.equals(Material.DARK_OAK_SLAB)
				|| mat.equals(Material.DARK_PRISMARINE_SLAB)
				|| mat.equals(Material.JUNGLE_SLAB)
				|| mat.equals(Material.NETHER_BRICK_SLAB)
				|| mat.equals(Material.OAK_SLAB)
				|| mat.equals(Material.PETRIFIED_OAK_SLAB)
				|| mat.equals(Material.PRISMARINE_BRICK_SLAB)
				|| mat.equals(Material.PRISMARINE_SLAB)
				|| mat.equals(Material.PURPUR_SLAB)
				|| mat.equals(Material.QUARTZ_SLAB)
				|| mat.equals(Material.RED_SANDSTONE_SLAB)
				|| mat.equals(Material.SANDSTONE_SLAB)
				|| mat.equals(Material.SPRUCE_SLAB)
				|| mat.equals(Material.STONE_BRICK_SLAB)
				|| mat.equals(Material.STONE_SLAB)) {
			return true;
		}
		return false;
	}

}
