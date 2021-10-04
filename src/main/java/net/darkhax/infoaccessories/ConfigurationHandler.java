package net.darkhax.infoaccessories;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

    public static Configuration config;

    public static boolean compassCoords = true;
    public static boolean invertF3 = false;

    public ConfigurationHandler (File file) {

        config = new Configuration(file);
        this.syncConfigData();
    }

    private void syncConfigData () {

        compassCoords = config.getBoolean("compassCoords", Configuration.CATEGORY_GENERAL, true, "When enabled, shows coordinates and direction data on the compass tooltip. Hides this information when disabled.");
        invertF3 = config.getBoolean("invertF3", Configuration.CATEGORY_GENERAL, false, "When enabled, the information of this mod will be shown only when the F3 menu is closed, rather than only when it is open. If enabled the normal F3 menu will not be edited at all.");

        if (config.hasChanged()) {
            config.save();
        }
    }
}
