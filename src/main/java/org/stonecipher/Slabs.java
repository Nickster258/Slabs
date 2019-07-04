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
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("slab-version")) {
			sendMessage(sender, "Slabs by Chibill - Maintained by Nickster258");
			sendMessage(sender, "Version: " + this.getDescription().getVersion());
		}

		if (cmd.getName().equalsIgnoreCase("slab")) {

			if(!(sender instanceof Player)) {
				sendMessage(sender, "This command can only be ran from ingame.");
				return true;
			}

			Player player = (Player) sender;

			ItemStack hand = player.getInventory().getItemInMainHand();

			if (isSlab(hand.getType())) {
				player.getInventory().remove(hand);
				hand.setItemMeta(getAdjustedMeta(hand.getItemMeta()));
				player.getInventory().addItem(hand);
				sendMessage(sender, "Your requested slab is now in your hand.");
			} else {
				if (player.getGameMode() != GameMode.SURVIVAL) {
					ItemStack item = new ItemStack(Material.STONE_SLAB);
					item.setItemMeta(getAdjustedMeta(item.getItemMeta()));
					player.getInventory().addItem(item);
					sendMessage(sender, "Your requested slab has been added inventory.");
				} else if (sender.hasPermission("Slab.survival")) {
					sendMessage(sender, "You must have a slab in your hand to get an Upside Down slab in Survival");
				}
			}
		}
		return true;
	}

	public void sendMessage(CommandSender sender, String message) {
		sender.sendMessage("§8[§7Slabs§8] §r" + message);
	}

	public ItemMeta getAdjustedMeta(ItemMeta meta) {
		meta.setDisplayName("Upside Down Slab");
		meta.addEnchant(Enchantment.DURABILITY, 1, false);
		return meta;
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
