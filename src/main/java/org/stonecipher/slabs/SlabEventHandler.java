package org.stonecipher.slabs;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Slab;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import org.bukkit.inventory.meta.ItemMeta;
import org.stonecipher.Slabs;

public class SlabEventHandler  implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		Block toPlace = event.getBlockPlaced();
		BlockData data = toPlace.getBlockData();
		if(Slabs.isSlab(data.getMaterial())) {
			if(Slabs.hasSlabLore(event.getItemInHand().getItemMeta())){
				if(!slabExists(event)) {
					SetInverted(event.getBlockPlaced());
				} else {
					Slab slab = (Slab) toPlace.getBlockData();
					slab.setType(Slab.Type.DOUBLE);
					toPlace.setBlockData(slab);
				}
			}
		}
	}

	public boolean slabExists(BlockPlaceEvent event) {
		if (Slabs.isSlab(event.getBlockReplacedState().getBlockData().getMaterial())) {
			return true;
		}
		return false;
	}
	
	public void SetInverted(Block b){
		Slab data = (Slab) b.getBlockData();
		data.setType(Slab.Type.TOP);
		b.setBlockData(data);
	}
	
}
