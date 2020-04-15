package me.byquanton.craftingtimer;

import me.byquanton.craftingtimer.commands.CraftCommand;
import me.byquanton.craftingtimer.listener.CraftingListener;
import me.byquanton.craftingtimer.util.ConfigUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class Craftingtimer extends JavaPlugin {

    public static ArrayList<UUID> players = new ArrayList<>();
    public static HashMap<UUID, Long> running = new HashMap<>();
    public static Plugin plugin;
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        plugin = this;
        System.out.println("Enabling CraftingTimer Plugin");
        ConfigUtil.generate(config);
        saveConfig();
        this.getCommand("craft").setExecutor(new CraftCommand());
        this.getServer().getPluginManager().registerEvents(new CraftingListener(),this);
        System.out.println("Enabled CraftingTimer Plugin");
    }

    @Override
    public void onDisable() {
        System.out.println("Disabling CraftingTimer Plugin");
        System.out.println("Disabled CraftingTimer Plugin");
    }
}
