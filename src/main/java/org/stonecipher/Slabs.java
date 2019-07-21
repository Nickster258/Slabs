package org.stonecipher;

import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import org.stonecipher.commands.Version;
import org.stonecipher.commands.Slab;
import org.stonecipher.events.SlabEventHandler;
import org.stonecipher.slabs.SlabEnchantment;

import java.lang.reflect.Field;

public class Slabs extends JavaPlugin {

	public static PluginDescriptionFile descriptionFile;
	public static NamespacedKey key;

	@Override
	public void onEnable() {

		descriptionFile = getDescription();
		key = new NamespacedKey(this, "UpsiDowner");

		registerSlabEnchant();

		getServer().getPluginManager().registerEvents(new SlabEventHandler(), this);
		getCommand("slab-version").setExecutor(new Version());
		getCommand("slab").setExecutor(new Slab());

	}

	public void registerSlabEnchant() {

		Enchantment slabEnchant = new SlabEnchantment(key);

		try {
			Field acceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
			acceptingNew.setAccessible(true);
			acceptingNew.set(null, true);
			Enchantment.registerEnchantment(slabEnchant);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void sendMessage(CommandSender sender, String message) {
		sender.sendMessage("§8[§7Slabs§8] §r" + message);
	}

}
