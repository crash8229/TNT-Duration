package com.gmail.mu304007.tntduration.block;

import java.util.Random;

import com.gmail.mu304007.tntduration.Main;
import com.gmail.mu304007.tntduration.entity.ModEntityTNTPrimed;
import com.gmail.mu304007.tntduration.entity.TNTPrimed0;
import com.gmail.mu304007.tntduration.entity.TNTPrimed1;
import com.gmail.mu304007.tntduration.entity.TNTPrimed10;
import com.gmail.mu304007.tntduration.entity.TNTPrimed11;
import com.gmail.mu304007.tntduration.entity.TNTPrimed12;
import com.gmail.mu304007.tntduration.entity.TNTPrimed13;
import com.gmail.mu304007.tntduration.entity.TNTPrimed14;
import com.gmail.mu304007.tntduration.entity.TNTPrimed15;
import com.gmail.mu304007.tntduration.entity.TNTPrimed16;
import com.gmail.mu304007.tntduration.entity.TNTPrimed2;
import com.gmail.mu304007.tntduration.entity.TNTPrimed3;
import com.gmail.mu304007.tntduration.entity.TNTPrimed4;
import com.gmail.mu304007.tntduration.entity.TNTPrimed5;
import com.gmail.mu304007.tntduration.entity.TNTPrimed6;
import com.gmail.mu304007.tntduration.entity.TNTPrimed7;
import com.gmail.mu304007.tntduration.entity.TNTPrimed8;
import com.gmail.mu304007.tntduration.entity.TNTPrimed9;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ModBlockTNT extends Block
{
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon bottomIcon;
    
    public static final CreativeTabs tntTab = new CreativeTabs("TNT Duration")
    {
    	@SideOnly(Side.CLIENT)
    	public Item getTabIconItem()
    	{
    		return Item.getItemFromBlock(ModBlocks.modTNT[0]);
    	}
    };
    
    private int fuse;
    private float power;
    private int block_id;
    private int flammability;
//    public List<TNTPrimed> primed = new ArrayList<TNTPrimed>();

	public ModBlockTNT(int fuse, float power, String name)
    {
        super(Material.tnt);
        this.setCreativeTab(tntTab);
        this.fuse = fuse * 20;
        this.setUnlocalizedName(name);
        this.setTextureName(Main.modid + ":" + name);
        this.setHardness(0.0F);
        this.setResistance(0.0F);
        this.setStepSound(soundTypeGrass);
        this.power = power;
        this.block_id = fuse;
        this.flammability = 100;
    }

	public int getFuse() {
		return fuse;
	}
	
	public int getBlock_id() {
		return block_id;
	}

	@Override
	public int tickRate(World worldIn) {
		return 50;
	}

	@Override
	public void updateTick(World par1World, int x, int y, int z, Random par5Random) {

		if(isByFire(par1World, x, y, z))
		{
			int chance = 300;
        	
			if (par1World.isBlockHighHumidity(x, y, z))
        	{
        		chance -= 50;
        	}
        	
        	if (par1World.rand.nextInt(chance) < flammability)
        	{
        		par1World.setBlockToAir(x, y, z);
    			par1World.setBlock(x, y, z, Blocks.fire);
    			this.primeTnt(par1World, x, y, z, 1, (EntityLivingBase)null, null);
        	} else {
        		int tickRate = this.tickRate(par1World) - par1World.rand.nextInt(this.tickRate(par1World));
        		par1World.scheduleBlockUpdate(x, y, z, this, tickRate);
        	}
			
		}
	}
	
	public static boolean isByFire(World world, int x, int y, int z)
	{
		for(int i = 0; i < 6; i++)
		{
			int x1 = x;
			int y1 = y;
			int z1 = z;
			
			switch (i) {
			case 0:
				x1 += 1;
				break;	
			case 1:
				x1 -= 1;
				break;
			case 2:
				y1 += 1;
				break;
			case 3:
				y1 -= 1;
				break;
			case 4:
				z1 += 1;
				break;
			case 5:
				z1 -= 1;
				break;
			default:
				break;
			}
			
//			System.out.println(world.getBlock(x1, y1, z1).getLocalizedName());
//			System.out.println(x1 + ":" + y1 + ":" + z1);
			
			if(world.getBlock(x1, y1, z1) == Blocks.fire)
			{
				return true;
			}
		}
		return false;
	}

	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IIconRegister)
    {
		this.blockIcon = par1IIconRegister.registerIcon(this.getTextureName());
        this.topIcon = par1IIconRegister.registerIcon(Main.modid + ":tntTop");
        this.bottomIcon = par1IIconRegister.registerIcon(Main.modid + ":tntBottom");
    }
	
	/**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
    	if(side == 0) {
    		return this.bottomIcon;
		} else if(side == 1) {
			return this.topIcon;
		} else {
			return this.blockIcon;
		}
    }

	/**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int x, int y, int z)
    {
        super.onBlockAdded(par1World, x, y, z);
        int tickRate = this.tickRate(par1World) - par1World.rand.nextInt(this.tickRate(par1World));
		par1World.scheduleBlockUpdate(x, y, z, this, tickRate);
        if (par1World.isBlockIndirectlyGettingPowered(x, y, z))
        {
        	this.primeTnt(par1World, x, y, z, 1, (EntityLivingBase)null, null);
            par1World.setBlockToAir(x, y, z);
        }
    }

	/**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World par1World, int x, int y, int z, Block block)
    {
        if (par1World.isBlockIndirectlyGettingPowered(x, y, z))
        {
//            this.onBlockDestroyedByPlayer(par1World, x, y, z, 1);
        	this.primeTnt(par1World, x, y, z, 1, (EntityLivingBase)null, null);
            par1World.setBlockToAir(x, y, z);
        } else if (isByFire(par1World, x, y, z))
		{
        	int tickRate = this.tickRate(par1World) - par1World.rand.nextInt(this.tickRate(par1World));
    		par1World.scheduleBlockUpdate(x, y, z, this, tickRate);
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion)
    {
    	primeTnt(par1World, par2, par3, par4, 2, null, par5Explosion);
    }

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
//    public void onBlockDestroyedByPlayer(World par1World, int x, int y, int z, int par5)
//    {
//        this.primeTnt(par1World, x, y, z, par5, (EntityLivingBase)null, null);
//    }

    public static ModEntityTNTPrimed getTNTEntity(World par1World, int id) {
    	
    	ModEntityTNTPrimed tntprimed = null;
    	
    	switch (id) {
		case 0:
			tntprimed = new TNTPrimed0(par1World);
			break;

		case 1:
			tntprimed = new TNTPrimed1(par1World);
			break;

		case 2:
			tntprimed = new TNTPrimed2(par1World);
			break;

		case 3:
			tntprimed = new TNTPrimed3(par1World);
			break;

		case 4:
			tntprimed = new TNTPrimed4(par1World);
			break;

		case 5:
			tntprimed = new TNTPrimed5(par1World);
			break;

		case 6:
			tntprimed = new TNTPrimed6(par1World);
			break;

		case 7:
			tntprimed = new TNTPrimed7(par1World);
			break;

		case 8:
			tntprimed = new TNTPrimed8(par1World);
			break;

		case 9:
			tntprimed = new TNTPrimed9(par1World);
			break;

		case 10:
			tntprimed = new TNTPrimed10(par1World);
			break;

		case 11:
			tntprimed = new TNTPrimed11(par1World);
			break;

		case 12:
			tntprimed = new TNTPrimed12(par1World);
			break;

		case 13:
			tntprimed = new TNTPrimed13(par1World);
			break;

		case 14:
			tntprimed = new TNTPrimed14(par1World);
			break;

		case 15:
			tntprimed = new TNTPrimed15(par1World);
			break;

		case 16:
			tntprimed = new TNTPrimed16(par1World);
			break;
			
		default:
			tntprimed = new TNTPrimed0(par1World);
			break;
		}
    	
		return tntprimed;
    	
    }
    
    public void primeTnt(World par1World, int x, int y, int z, int par5, EntityLivingBase par6EntityLivingBase, Explosion par7Explosion)
    {
    	ModEntityTNTPrimed tntprimed = getTNTEntity(par1World, block_id);
    	tntprimed.setPower(power);
    	
    	if (par5 == 1 && !par1World.isRemote) {
    		tntprimed.setSpawn((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), par6EntityLivingBase);
    		tntprimed.setFuse(fuse);
    		tntprimed.setPower(this.power);
			par1World.spawnEntityInWorld(tntprimed);
			par1World.playSoundAtEntity(tntprimed, "game.tnt.primed", 1.0F, 1.0F);
		} 
    	else if (par5 == 2 && !par1World.isRemote) {
    		tntprimed.setSpawn((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), par7Explosion.getExplosivePlacedBy());
    		if (block_id != 0) {
    			tntprimed.setFuse(par1World.rand.nextInt(20) + 10);
    		}
    		tntprimed.setPower(this.power);
    		par1World.spawnEntityInWorld(tntprimed);
    	}
    	
    } 
    
    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().getItem() == Items.flint_and_steel)
        {
            this.primeTnt(par1World, x, y, z, 1, par5EntityPlayer, null);
            par1World.setBlockToAir(x, y, z);
            par5EntityPlayer.getCurrentEquippedItem().damageItem(1, par5EntityPlayer);
            return true;
        }
        else
        {
            return super.onBlockActivated(par1World, x, y, z, par5EntityPlayer, par6, par7, par8, par9);
        }
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World par1World, int x, int y, int z, Entity par5Entity)
    {
        if (par5Entity instanceof EntityArrow && !par1World.isRemote)
        {
            EntityArrow entityarrow = (EntityArrow)par5Entity;

            if (entityarrow.isBurning())
            {
                this.primeTnt(par1World, x, y, z, 1, entityarrow.shootingEntity instanceof EntityLivingBase ? (EntityLivingBase)entityarrow.shootingEntity : null, null);
                par1World.setBlockToAir(x, y, z);
            }
        }
    }

    /**
     * Return whether this block can drop from an explosion.
     */
    public boolean canDropFromExplosion(Explosion par1Explosion)
    {
        return false;
    }
    
}