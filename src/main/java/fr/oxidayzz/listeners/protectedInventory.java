package fr.oxidayzz.listeners;

import fr.oxidayzz.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class protectedInventory implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Inventory clickedInventory = e.getInventory();
        if (Main.getProtectedInventory().contains(clickedInventory)) {
            e.setCancelled(true);
        }
    }    
}
