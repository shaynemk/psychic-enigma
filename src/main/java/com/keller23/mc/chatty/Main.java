package com.keller23.mc.chatty;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = Refs.MODID, version = Refs.VERSION)
public class Main
{
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());

        System.out.println("Loading Config");
        config.load();

        Refs.DEBUG = config.get(Refs.CATEGORY_DEBUG, Refs.NAME_DEBUG, Refs.DEFAULT_DEBUG_VALUE);
        Refs.DEBUG.comment = Refs.DEFAULT_DEBUG_COMMENT;

        Refs.REMOTE_ENABLED = config.get(Refs.CATEGORY_REMOTE, Refs.NAME_REMOTE_ENABLED, Refs.DEFAULT_REMOTE_ENABLED_VALUE);
        Refs.REMOTE_ENABLED.comment = Refs.DEFAULT_REMOTE_ENABLED_COMMENT;

        Refs.REMOTE_PORT = config.get(Refs.CATEGORY_REMOTE, Refs.NAME_REMOTE_PORT, Refs.DEFAULT_REMOTE_PORT_VALUE);
        Refs.REMOTE_PORT.comment = Refs.DEFAULT_REMOTE_PORT_COMMENT;

        config.save();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("Hello, Minecraft!");
        System.out.println("Oh, how I missed you...");

        if(Refs.DEBUG.getBoolean()) {
            System.out.println("Registering ChatHandler");
        }


        MinecraftForge.EVENT_BUS.register(new ChatHandler());
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new ChattyCommand());
        event.registerServerCommand(new RainlessCommand());

        if(Refs.REMOTE_ENABLED.getBoolean()) {
            // TODO: 4/22/16 add in call to init server remote listener
        }
    }
}
