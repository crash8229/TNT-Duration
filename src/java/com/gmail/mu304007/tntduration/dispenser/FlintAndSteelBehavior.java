package com.gmail.mu304007.tntduration.dispenser;

import com.gmail.mu304007.tntduration.block.ModBlocks;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class FlintAndSteelBehavior extends BehaviorDefaultDispenseItem {

        private boolean sound = true;
        /**
         * Dispense the specified stack, play the dispense sound and spawn particles.
         */
        protected ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack)
        {
            EnumFacing enumfacing = BlockDispenser.getFacingDirection(par1IBlockSource.getBlockMetadata());
            World world = par1IBlockSource.getWorld();
            int i = par1IBlockSource.getXInt() + enumfacing.getFrontOffsetX();
            int j = par1IBlockSource.getYInt() + enumfacing.getFrontOffsetY();
            int k = par1IBlockSource.getZInt() + enumfacing.getFrontOffsetZ();
            
            for (int c = 0; c < ModBlocks.modTNT.length; c++)
            {
            	if (world.getBlock(i, j, k) == ModBlocks.modTNT[c])
            	{
            		ModBlocks.modTNT[c].onBlockDestroyedByPlayer(world, i, j, k, 1);
            		world.setBlockToAir(i, j, k);
            	}
            }
            
            if (world.isAirBlock(i, j, k))
            {
                world.setBlock(i, j, k, Blocks.fire);

                if (par2ItemStack.attemptDamageItem(1, world.rand))
                {
                    par2ItemStack.stackSize = 0;
                }
            }
            else if (world.getBlock(i, j, k) == Blocks.tnt)
            {
                Blocks.tnt.onBlockDestroyedByPlayer(world, i, j, k, 1);
                world.setBlockToAir(i, j, k);
            }
            else
            {
                this.sound = false;
            }

            return par2ItemStack;
        }
        /**
         * Play the dispense sound from the specified block.
         */
        protected void playDispenseSound(IBlockSource par1IBlockSource)
        {
            if (this.sound)
            {
                par1IBlockSource.getWorld().playAuxSFX(1000, par1IBlockSource.getXInt(), par1IBlockSource.getYInt(), par1IBlockSource.getZInt(), 0);
            }
            else
            {
                par1IBlockSource.getWorld().playAuxSFX(1001, par1IBlockSource.getXInt(), par1IBlockSource.getYInt(), par1IBlockSource.getZInt(), 0);
            }
        }
    
}
