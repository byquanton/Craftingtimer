package me.byquanton.craftingtimer.listener;

import me.byquanton.craftingtimer.Craftingtimer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.CraftingInventory;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;


public class CraftingListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void startcrafting(InventoryOpenEvent event){
        if(event.getInventory() instanceof CraftingInventory){
            if(Craftingtimer.players.contains(event.getPlayer().getUniqueId())){
                if(Craftingtimer.running.containsKey(event.getPlayer().getUniqueId())){
                    Craftingtimer.running.remove(event.getPlayer().getUniqueId());
                }else {
                    Craftingtimer.running.put(event.getPlayer().getUniqueId(), System.nanoTime());

                }


            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void closeinventory(InventoryCloseEvent event){
        if(event.getInventory() instanceof CraftingInventory){
            if(Craftingtimer.running.containsKey(event.getPlayer().getUniqueId())){
                Craftingtimer.running.remove(event.getPlayer().getUniqueId());
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void stopcrafting(CraftItemEvent event){
        if(Craftingtimer.players.contains(event.getWhoClicked().getUniqueId())){
            if(Craftingtimer.running.containsKey(event.getWhoClicked().getUniqueId())){
                double time = (double)(System.nanoTime()-Craftingtimer.running.get(event.getWhoClicked().getUniqueId()))/1000000000;
                time = Math.round(100.0 * time) / 100.0;
                String itemname;
                try{
                    itemname = event.getCurrentItem().getType().getKey().toString();
                }catch (NoSuchMethodError e){
                    itemname = event.getCurrentItem().getType().name().toString();
                }

                String buildmessage = Craftingtimer.plugin.getConfig().getString("message.time").replaceAll("%time%",String.valueOf(time)).replaceAll("%item%", Objects.requireNonNull(itemname)).replaceAll("&","§");
                event.getWhoClicked().sendMessage(buildmessage);
                Craftingtimer.running.remove(event.getWhoClicked().getUniqueId());
                Craftingtimer.running.put(event.getWhoClicked().getUniqueId(), System.nanoTime());
            }
        }

    }
}
