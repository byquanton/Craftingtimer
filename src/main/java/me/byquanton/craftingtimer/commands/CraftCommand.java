package me.byquanton.craftingtimer.commands;

import me.byquanton.craftingtimer.Craftingtimer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;


public class CraftCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("start")) {
                    if (Craftingtimer.players.contains(player.getUniqueId())){
                        player.sendMessage(Objects.requireNonNull(Craftingtimer.plugin.getConfig().getString("message.already_activated").replaceAll("&","§")));
                    }else {
                        Craftingtimer.players.add(player.getUniqueId());
                        player.sendMessage(Objects.requireNonNull(Craftingtimer.plugin.getConfig().getString("message.activate").replaceAll("&","§")));
                        return true;
                    }
                }else if (args[0].equalsIgnoreCase("stop")) {
                    if (Craftingtimer.players.contains(player.getUniqueId())){
                        Craftingtimer.players.remove(player.getUniqueId());
                        player.sendMessage(Objects.requireNonNull(Craftingtimer.plugin.getConfig().getString("message.deactivate").replaceAll("&","§")));
                        return true;
                    }else {
                        player.sendMessage(Objects.requireNonNull(Craftingtimer.plugin.getConfig().getString("message.already_deactivated").replaceAll("&","§")));
                    }
                }else if(args[0].equalsIgnoreCase("toggle")) {
                    if (Craftingtimer.players.contains(player.getUniqueId())){
                        Craftingtimer.players.remove(player.getUniqueId());
                        player.sendMessage(Objects.requireNonNull(Craftingtimer.plugin.getConfig().getString("message.deactivate").replaceAll("&","§")));
                        return true;
                    }else {
                        Craftingtimer.players.add(player.getUniqueId());
                        player.sendMessage(Objects.requireNonNull(Craftingtimer.plugin.getConfig().getString("message.activate").replaceAll("&","§")));
                        return true;
                    }
                }
            }

        }
        return false;
    }


}
