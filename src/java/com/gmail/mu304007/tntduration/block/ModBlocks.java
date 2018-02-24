package com.gmail.mu304007.tntduration.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;


public final class ModBlocks {
	
	public static ModBlockTNT[] modTNT = new ModBlockTNT[17];
	
	public static final void init()
	{
		for(int i = 0; i < modTNT.length; i++)
		{
			String name = "TNT(" + Integer.toString(i) + ")";
			modTNT[i] = new ModBlockTNT(i, 4.0F, name);
			GameRegistry.registerBlock(modTNT[i], name);
			Blocks.fire.setFireInfo(modTNT[i], 15, 1);
//			Blocks.fire.setFireInfo(modTNT[i], 1000, 0);
		}
	}

}
