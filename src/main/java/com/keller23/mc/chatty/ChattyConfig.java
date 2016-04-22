package com.keller23.mc.chatty;


public class ChattyConfig {

    public static void load() {
        /*System.out.println("Loading ChattyConfig");
        Configuration config = null;
        File cfgFile = new File(Loader.instance().getConfigDir(), "chatty.cfg");
        try {
            config = new Configuration(cfgFile);
        } catch (Exception e) {
            System.out.println("Error loading " + Refs.CONFIGFILE + ", deleting and reseting.");

            if(cfgFile.exists()) cfgFile.delete();

            config = new Configuration(cfgFile);
        }

        if(!config.isChild) {
            config.load();
            Property enableGlobalConfig = config.get(Configuration.CATEGORY_GENERAL, "enableGlobalConfig", false);
            if(enableGlobalConfig.getBoolean(false)) {
                Configuration.enableGlobalConfig();
            }
        }*/

        //Property prop = config.get(Configuration.CATEGORY_GENERAL, "")
    }
}
