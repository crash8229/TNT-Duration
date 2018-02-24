package com.gmail.mu304007.tntduration;

import java.util.Iterator;
import java.util.List;

import com.gmail.mu304007.tntduration.entity.ModEntityTNTPrimed;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

public class ChunkLoad implements LoadingCallback {
	private static ChunkLoad instance;
	
	public static ChunkLoad getInstance() {
		if(instance == null) {
			instance = new ChunkLoad();
		}

		return instance;
	}
	

	
	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world) {
		Iterator<Ticket> ticketList = tickets.iterator();
		while(ticketList.hasNext()) {
			Ticket ticket = ticketList.next();
			Entity entity = ticket.getEntity();
			if(entity instanceof ModEntityTNTPrimed) {
				ModEntityTNTPrimed tntprimed = (ModEntityTNTPrimed)entity;
				tntprimed.setTicket(ticket);
//				System.out.println("Reloading Chunks");
				tntprimed.forceChunks(tntprimed.chunkCoordX, tntprimed.chunkCoordZ);
			} else {
				ForgeChunkManager.releaseTicket(ticket);
			}
		}
	}

}
