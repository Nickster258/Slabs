package org.stonecipher.slabs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.data.type.Slab;
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
        return mat.createBlockData() instanceof Slab;
    }

}
