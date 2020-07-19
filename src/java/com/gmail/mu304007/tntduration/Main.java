package com.gmail.mu304007.tntduration;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.modid, name = Main.modname, version = Main.version)
public class Main {

    public static final String modid = "tntduration";
    public static final String modname = "TNT Duration Mod";
    public static final String version = "1.0";
        
//    @Instance
//    public static Main instance = new Main();
    @Instance("tntduration")
    public static Main instance;
    
    public static Main getMod() {
    	return instance;
    }
    
    @SidedProxy(clientSide="com.gmail.mu304007.tntduration.ClientProxy", serverSide="com.gmail.mu304007.tntduration.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

}
