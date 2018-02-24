package com.gmail.mu304007.tntduration.render;

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

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ModRender {

	public static final void init() {
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed0.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed1.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed2.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed3.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed4.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed5.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed6.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed7.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed8.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed9.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed10.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed11.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed12.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed13.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed14.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed15.class, new ModRenderTNTPrimed());
		RenderingRegistry.registerEntityRenderingHandler(TNTPrimed16.class, new ModRenderTNTPrimed());
	}
	
}
