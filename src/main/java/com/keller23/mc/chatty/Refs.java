package com.keller23.mc.chatty;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.apache.logging.log4j.Logger;

class Refs {

    static final String MODID = "chatty";
    static final String MODNAME = "Chatty";
    static final String VERSION = "0.1";

    static final String CLIENT_PROXY = "com.keller23.mc.chatty.proxy.ClientProxy";
    static final String COMMON_PROXY = "com.keller23.mc.chatty.proxy.CommonProxy";

    static Logger Log;

    static final String CMD = "/" + MODID;

    // Config File Stuff
    static Configuration Config;
    public static final String CONFIGFILE = "chatty.cfg";
    static final String CATEGORY_DEBUG = "Debugging";
    static final String CATEGORY_REMOTE = "Remote Control";

    // Properties
    static Property DEBUG;
    static Property REMOTE_ENABLED;
    static Property REMOTE_PORT;

    // Property Names
    static final String NAME_DEBUG = "debugging";
    static final String NAME_REMOTE_ENABLED = "remoteEnabled";
    static final String NAME_REMOTE_PORT = "remotePort";

    // Property Default Comments
    static final String DEFAULT_DEBUG_COMMENT = "Enable Debugging.";
    static final String DEFAULT_REMOTE_ENABLED_COMMENT = "Is the remote control server enabled. Restart required.";
    static final String DEFAULT_REMOTE_PORT_COMMENT = "Use this port for remote control.";

    // Property Default Values
    static final boolean DEFAULT_DEBUG_VALUE = false;
    static final boolean DEFAULT_REMOTE_ENABLED_VALUE = false;
    static final int DEFAULT_REMOTE_PORT_VALUE = 2428;

}
