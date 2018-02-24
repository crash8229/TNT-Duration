package com.gmail.mu304007.tntduration.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.gmail.mu304007.tntduration.Main;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

public class ModEntityTNTPrimed extends Entity
{
    /** How long the fuse is */
    public int fuse;
    public float power;
    public EntityLivingBase tntPlacedBy;
//    @Nullable
    private Ticket ticket;
    private Set<ChunkCoordIntPair> chunks;
    private Set<ChunkCoordIntPair> prevChunks;

    public ModEntityTNTPrimed(World par1World)
    {
        super(par1World);
        this.fuse = 80;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
    }
    
//    public void setSpawn(World par1World, double x, double y, double z, EntityLivingBase par5EntityLivingBase)
    public void setSpawn(double x, double y, double z, EntityLivingBase par5EntityLivingBase)
    {
    	this.setPosition(x, y, z);
//    	float f = (float)(Math.random() * Math.PI * 2.0D);
//    	this.motionX = (double)(-((float)Math.sin((double)f)) * 0.02F);
    	this.motionY = 0.20000000298023224D;
//    	this.motionZ = (double)(-((float)Math.cos((double)f)) * 0.02F);
//    	this.prevPosX = x;
//    	this.prevPosY = y;
//    	this.prevPosZ = z;
    	this.tntPlacedBy = par5EntityLivingBase;
    	this.power= 4.0F;
    }
    
    public int getFuse() {
        return fuse;
    }
    
    public void setFuse(int fuse) {
        this.fuse = fuse;
    }
    
    public float getPower() {
        return power;
    }
    
    public void setPower(float power) {
        this.power = power;
    }

	protected void entityInit() {}

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }
    
    protected Ticket getTicketFromForge() {
    	return ForgeChunkManager.requestTicket(Main.getMod(), this.worldObj, Type.ENTITY);
    }

    public void getTicket() {
//    	System.out.println("Getting Ticket");
    	if(this.ticket == null) {
    		Ticket newTicket = this.getTicketFromForge();
    		if(newTicket != null) {
    			newTicket.getModData();
    			newTicket.setChunkListDepth(9);
    			newTicket.bindEntity(this);
    			this.setTicket(newTicket);
    			this.forceChunks(this.chunkCoordX, this.chunkCoordZ);
//    			return true;
    		}
    	}
//    	return false;
    }
    
    public void forceChunks(int xChunk, int zChunk) {
    	if (this.ticket != null) {
    		this.chunks = this.getChunks(xChunk, zChunk);
    		if(prevChunks == null || !chunks.equals(prevChunks)) {
    			
    			
    			
    			
    			
    			
//    			System.out.println("Forcing Chunks");
    			Iterator<ChunkCoordIntPair> chunkList = this.chunks.iterator();
    			ChunkCoordIntPair chunk;
    			while(chunkList.hasNext()) {
    				chunk = (ChunkCoordIntPair)chunkList.next();
    				ForgeChunkManager.forceChunk(this.ticket, chunk);
    			}
    			ForgeChunkManager.reorderChunk(this.ticket, new ChunkCoordIntPair(xChunk, zChunk));
    			prevChunks = chunks;
    		} else {
//    			System.out.println("Not Forcing Chunks");
    		}
//    		System.out.println();
    	}
    }
    
    public void setTicket(Ticket ticket) {
    	if(this.ticket != ticket) {
			ForgeChunkManager.releaseTicket(this.ticket);
		}
		this.ticket = ticket;
    }
    
    protected void releaseTicket() {
        ForgeChunkManager.releaseTicket(this.ticket);
        this.ticket = null;
    }
    
	public Set<ChunkCoordIntPair> getChunks(int xChunk, int zChunk) {
		HashSet<ChunkCoordIntPair> chunks = new HashSet<ChunkCoordIntPair>();
//		System.out.println("Chunk Coordinates: ");
		int radius = 1;
		for(int x = xChunk - radius; x <= xChunk + radius; x++) {
			for(int z = zChunk - radius; z <= zChunk + radius; z++) {
				chunks.add(new ChunkCoordIntPair(x, z));
//				System.out.println("(" + x + ", " + z + ")");
			}
		}
		
//		for(int z = zChunk - radius; z <= zChunk; z++) {
//			chunks.add(new ChunkCoordIntPair(xChunk, z));
//			System.out.println("(" + xChunk + ", " + z + ")");
//		}
		
//		System.out.println();
		return chunks;
	}
    
    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
    	if (this.ticket == null) {
    		this.getTicket();
    	} else {
    		forceChunks(this.chunkCoordX, this.chunkCoordZ);
    	}
    	
    	System.out.println((int)Math.floor(this.posX % 16));
    	
    	System.out.println("X: " + this.motionX);
    	System.out.println("Y: " + this.motionY);
    	System.out.println("Z: " + this.motionZ);
    	System.out.println();
    	
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033D;
        
        
//        this.moveEntity(this.motionX, 1D, this.motionZ);
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
//        this.motionX *= 0.9800000190734863D;
//        this.motionY *= 0.9800000190734863D;
//        this.motionZ *= 0.9800000190734863D;

//        if (this.onGround)
//        {
//            this.motionX *= 0.699999988079071D;
//            this.motionZ *= 0.699999988079071D;
//            this.motionY *= -0.5D;
//        }

        if (this.fuse-- <= 0)
        {
            this.setDead();
            this.releaseTicket();

            if (!this.worldObj.isRemote)
            {
                this.explode();
            }
        }
        else
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    private void explode()
    {
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, power, true);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setByte("Fuse", (byte)this.fuse);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        this.fuse = par1NBTTagCompound.getByte("Fuse");
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    /**
     * returns null or the entityliving it was placed or ignited by
     */
    public EntityLivingBase getTntPlacedBy()
    {
        return this.tntPlacedBy;
    }
}