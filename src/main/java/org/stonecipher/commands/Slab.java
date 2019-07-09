package org.stonecipher.commands;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import org.stonecipher.Slabs;
import org.stonecipher.slabs.SlabBlock;

public class Slab implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            Slabs.sendMessage(commandSender, "This command can only be ran from ingame.");
            return true;
        }

        Player player = (Player) commandSender;

        ItemStack hand = player.getInventory().getItemInMainHand();

        SlabBlock slab = new SlabBlock(hand.getType());

        if (!hand.getType().equals(Material.AIR) && SlabBlock.hasSlabLore(hand.getItemMeta())){
            Slabs.sendMessage(commandSender, "An UpsiDowner slab is already in hand.");
        } else if(slab.isValid()) {
            player.getInventory().remove(hand);
            player.getInventory().setItemInMainHand(slab.getSlab());
            Slabs.sendMessage(commandSender, "Your requested slab is now in your hand.");
        } else if (player.getGameMode() == GameMode.SURVIVAL) {
            Slabs.sendMessage(commandSender, "You must have a slab in your hand to get an Upside Down slab in Survival");
        } else {
            player.getInventory().addItem(new SlabBlock(Material.STONE_SLAB).getSlab());
            Slabs.sendMessage(commandSender, "Your requested slab has been added inventory.");
        }
        return true;

    }
}
