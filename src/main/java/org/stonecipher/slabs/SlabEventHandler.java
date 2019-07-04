package org.stonecipher.slabs;

import org.bukkit.block.Block;
import org.bukkit.block.data.type.Slab;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import org.stonecipher.Slabs;

public class SlabEventHandler  implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		if((event.getItemInHand().getItemMeta().getDisplayName().equals("Upside Down Slab"))){
			if(Slabs.isSlab(event.getBlockPlaced().getBlockData().getMaterial())) {
				SetInverted(event.getBlockPlaced());
			}
		}

	}
	
	public void SetInverted(Block b){
		Slab data = (Slab) b.getBlockData();
		data.setType(Slab.Type.TOP);
		b.setBlockData(data);
	}
	
}
