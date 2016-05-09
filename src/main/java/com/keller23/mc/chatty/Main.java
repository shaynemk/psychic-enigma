package com.keller23.mc.chatty;

import com.keller23.mc.chatty.KnockKnock.KnockKnockServer;
import com.keller23.mc.chatty.proxy.CommonProxy;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import java.io.IOException;

import static com.keller23.mc.chatty.Refs.Config;
import static com.keller23.mc.chatty.Refs.Log;


@Mod(modid = Refs.MODID, version = Refs.VERSION)
public class Main
{

    @SidedProxy(clientSide = Refs.CLIENT_PROXY, serverSide = Refs.COMMON_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Log = FMLLog.getLogger();

        Log.debug("Chatty PreInit");

        Config = new Configuration(event.getSuggestedConfigurationFile());
        Config.load();

        Refs.DEBUG = Config.get(Refs.CATEGORY_DEBUG, Refs.NAME_DEBUG, Refs.DEFAULT_DEBUG_VALUE);
        Refs.DEBUG.comment = Refs.DEFAULT_DEBUG_COMMENT;

        Refs.REMOTE_ENABLED = Config.get(Refs.CATEGORY_REMOTE, Refs.NAME_REMOTE_ENABLED, Refs.DEFAULT_REMOTE_ENABLED_VALUE);
        Refs.REMOTE_ENABLED.comment = Refs.DEFAULT_REMOTE_ENABLED_COMMENT;

        Refs.REMOTE_PORT = Config.get(Refs.CATEGORY_REMOTE, Refs.NAME_REMOTE_PORT, Refs.DEFAULT_REMOTE_PORT_VALUE);
        Refs.REMOTE_PORT.comment = Refs.DEFAULT_REMOTE_PORT_COMMENT;

        Config.save();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Log.debug("Chatty Init");
        Log.info("Hello, Minecraft!");
        Log.info("Oh, how I missed you...");

        if(Refs.DEBUG.getBoolean()) {
            Log.debug("Registering ChatHandler");
        }


        MinecraftForge.EVENT_BUS.register(new ChatHandler());
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) throws IOException {
        Log.debug("Chatty serverLoad");
        event.registerServerCommand(new ChattyCommand());
        event.registerServerCommand(new RainlessCommand());

        if(Refs.REMOTE_ENABLED.getBoolean()) {
            //KKMultiServer.main();
            KnockKnockServer.main();
        }
    }
}
