package com.gmail.mu304007.tntduration;

import com.gmail.mu304007.tntduration.block.ModBlocks;
import com.gmail.mu304007.tntduration.dispenser.ModDispenserBehavior;
import com.gmail.mu304007.tntduration.entity.ModEntities;
import com.gmail.mu304007.tntduration.recipes.ModRecipes;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.ForgeChunkManager;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		ForgeChunkManager.setForcedChunkLoadingCallback(Main.getMod(), ChunkLoad.getInstance());
		ModEntities.init();
		ModBlocks.init();
    }

    public void init(FMLInitializationEvent e) {
    	ModRecipes.init();
    }

    public void postInit(FMLPostInitializationEvent e) {
    	ModDispenserBehavior.init();
    }

}
	