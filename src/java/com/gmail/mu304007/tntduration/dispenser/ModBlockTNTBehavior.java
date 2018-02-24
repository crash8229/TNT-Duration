package com.gmail.mu304007.tntduration.dispenser;

import com.gmail.mu304007.tntduration.block.ModBlocks;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ModBlockTNTBehavior extends BehaviorDefaultDispenseItem {
	
	private int id;
	
	public ModBlockTNTBehavior(int id)
	{
		this.id = id;
	}
	
	protected ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack)
    {
        EnumFacing enumfacing = BlockDispenser.getFacingDirection(par1IBlockSource.getBlockMetadata());
        World world = par1IBlockSource.getWorld();
        int i = par1IBlockSource.getXInt() + enumfacing.getFrontOffsetX();
        int j = par1IBlockSource.getYInt() + enumfacing.getFrontOffsetY();
        int k = par1IBlockSource.getZInt() + enumfacing.getFrontOffsetZ();
        
        ModBlocks.modTNT[id].primeTnt(world, i, j, k, 1, (EntityLivingBase)null, null);
		
        --par2ItemStack.stackSize;
        return par2ItemStack;
    }

}
