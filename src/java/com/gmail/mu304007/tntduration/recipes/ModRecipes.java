package com.gmail.mu304007.tntduration.recipes;

import com.gmail.mu304007.tntduration.block.ModBlocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {
	public static void init() {
		
		//GameRegistry.addRecipe(new ItemStack(ModBlocks.tutorialBlock), "##", "##", '#', ModItems.tutorialItem);
		//GameRegistry.addShapelessRecipe(new ItemStack(ModItems.tutorialItem), Items.redstone, new ItemStack(Items.dye, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.tnt), ModBlocks.modTNT[4]);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.modTNT[4]), Blocks.tnt);
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.modTNT[4]), "#I#", "III", "#I#", '#', Blocks.sand, 'I', Items.gunpowder);

	}
}
