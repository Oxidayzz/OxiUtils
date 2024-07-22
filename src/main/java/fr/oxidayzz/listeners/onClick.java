package fr.oxidayzz.listeners;

import fr.oxidayzz.Main;
import fr.oxidayzz.function.MenuUtils;
import fr.oxidayzz.function.WorldUtils;
import fr.oxidayzz.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.World;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class onClick implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
        if(item != null && item.getType() == Material.COMPASS && item.getItemMeta().getDisplayName().equals("§d§lMenu §l§8>> §fClic-droit")) {
            MenuUtils.configMenu(player);
        }
    }

    @EventHandler
    public void onInteract(InventoryClickEvent e) {
        if(e.getCurrentItem() == null) return;

        Player player = (Player) e.getWhoClicked();

        switch(e.getCurrentItem().getType()) {


            case STAINED_GLASS_PANE:
                if(e.getCurrentItem().getDurability() == 10) {
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§7§k55555")) {
                        e.setCancelled(true);
                    }
            }
            case IRON_DOOR:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aMonde")) {
                    e.setCancelled(true);
                    MenuUtils.mondeMenu(player);
                }
            case ARROW:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSortir")) {
                    e.setCancelled(true);
                    player.closeInventory();
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aRetour")) {
                    e.setCancelled(true);
                    MenuUtils.configMenu(player);
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Annuler")) {
                    e.setCancelled(true);
                    MenuUtils.mondeMenu(player);
                    player.sendMessage(Main.Prefix + "Annulation ...");
                }
            case SLIME_BALL:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aCrée un monde")) {
                    e.setCancelled(true);
                    if(Bukkit.getWorld("Arena") == null) {
                        player.closeInventory();
                        WorldUtils.createWorld(player,"Arena");
                    }else {
                        MenuUtils.confirmationWorldMenu(player);
                        player.sendMessage(Main.EPrefix + "Un monde existe deja ! Voullez vous le supprimez pour en recrée un ? ");
                    }
                }
            case REDSTONE:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cSupprimé le monde")) {
                    e.setCancelled(true);
                    WorldUtils.deleteWorld(player, "Arena");
                }
            case REDSTONE_BLOCK:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cRefuser")) {
                    e.setCancelled(true);
                    player.sendMessage(Main.Prefix + "Suppression du monde annuler !");
                    player.closeInventory();
                    MenuUtils.mondeMenu(player);
                }
            case EMERALD_BLOCK:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aConfirmer")) {
                    e.setCancelled(true);
                    player.closeInventory();
                    WorldUtils.deleteWorld(player, "Arena");
                    WorldUtils.createWorld(player, "Arena");
                }
            default:
                break;
        }
    }
}
