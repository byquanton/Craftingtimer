package me.byquanton.craftingtimer.util;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUtil {
    public static void generate(FileConfiguration config){
        config.options().header("# Crafting Timer Plugin. Created by byquanton");
        config.options().copyHeader(true);
        config.addDefault("message.activate","Der Timer wurde aktiviert");
        config.addDefault("message.already_activated","Der Timer ist schon aktiviert");
        config.addDefault("message.deactivate","Der Timer wurde deaktiviert");
        config.addDefault("message.already_deactivated","Der Timer ist schon deaktiviert");
        config.addDefault("message.time","Du hast &2%time%s&r gebraucht um &2%item%&r zu craften");
        config.options().copyDefaults(true);
    }
}
