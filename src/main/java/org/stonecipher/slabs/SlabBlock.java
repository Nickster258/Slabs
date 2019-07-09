package org.stonecipher.slabs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.stonecipher.Slabs;

import java.util.ArrayList;

public class SlabBlock {

    private Material slabType;
    private boolean isValid;

    public SlabBlock(Material slabType) {
        if (isSlab(slabType)) {
            this.slabType = slabType;
            this.isValid = true;
        } else {
            this.isValid = false;
        }
    }

    public boolean setSlabType(Material slabType) {
        if(isSlab(slabType)) {
            this.slabType = slabType;
            this.isValid = true;
            return true;
        }
        return false;
    }

    public boolean isValid() {
        return isValid;
    }

    public ItemStack getSlab() {
        ItemStack slab = new ItemStack(this.slabType);
        slab.setItemMeta(getAdjustedMeta(slab.getItemMeta()));
        slab.addEnchantment(new SlabEnchantment(Slabs.key), 0);
        return slab;
    }

    public ItemMeta getAdjustedMeta(ItemMeta meta) {
        meta.setDisplayName("Upside Down Slab");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.BOLD + "UpsiDowner");
        meta.setLore(lore);
        return meta;
    }

    public static boolean hasSlabLore(ItemMeta meta) {
        if (!meta.hasLore()) {
            return false;
        }
        if (meta.getLore().contains(ChatColor.BOLD + "UpsiDowner")) {
            return true;
        }
        return false;
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
