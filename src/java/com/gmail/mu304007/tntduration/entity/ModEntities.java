package com.gmail.mu304007.tntduration.entity;

import java.util.ArrayList;
import java.util.List;

import com.gmail.mu304007.tntduration.Main;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;

public class ModEntities {
	
	public static final void init() {
		List<Class<? extends Entity>> tntPrimed = new ArrayList<Class<? extends Entity>>();
		
		tntPrimed.add(TNTPrimed0.class);
		tntPrimed.add(TNTPrimed1.class);
		tntPrimed.add(TNTPrimed2.class);
		tntPrimed.add(TNTPrimed3.class);
		tntPrimed.add(TNTPrimed4.class);
		tntPrimed.add(TNTPrimed5.class);
		tntPrimed.add(TNTPrimed6.class);
		tntPrimed.add(TNTPrimed7.class);
		tntPrimed.add(TNTPrimed8.class);
		tntPrimed.add(TNTPrimed9.class);
		tntPrimed.add(TNTPrimed10.class);
		tntPrimed.add(TNTPrimed11.class);
		tntPrimed.add(TNTPrimed12.class);
		tntPrimed.add(TNTPrimed13.class);
		tntPrimed.add(TNTPrimed14.class);
		tntPrimed.add(TNTPrimed15.class);
		tntPrimed.add(TNTPrimed16.class);
		
		for (int i = 0; i < tntPrimed.size(); i++)
		{
			registerEntity(tntPrimed, i);
		}
	}
	
	public static final void registerEntity(List<Class<? extends Entity>> listEntity, int index)
	{
		int id = EntityRegistry.findGlobalUniqueEntityId();
		String name = "TNTPrimed(" + Integer.toString(index) + ")";
		EntityRegistry.registerGlobalEntityID(listEntity.get(index), name, id);
//    	EntityRegistry.registerModEntity(listEntity.get(index), name, id, Main.instance, 256, 1, false);
		EntityRegistry.registerModEntity(listEntity.get(index), name, id, Main.instance, 256, 1, false);
	}

}
