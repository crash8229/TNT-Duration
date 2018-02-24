package com.gmail.mu304007.tntduration.dispenser;

import com.gmail.mu304007.tntduration.block.ModBlocks;

import net.minecraft.block.BlockDispenser;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ModDispenserBehavior {

	public static final void init() {
		BlockDispenser.dispenseBehaviorRegistry.putObject(Items.flint_and_steel, new FlintAndSteelBehavior());
		
		for (int i = 0; i < ModBlocks.modTNT.length; i++)
		{
			BlockDispenser.dispenseBehaviorRegistry.putObject(Item.getItemFromBlock(ModBlocks.modTNT[i]), new ModBlockTNTBehavior(i));
		}
		
	}

}
