package fr.oxidayzz.function;

import fr.oxidayzz.Main;
import fr.oxidayzz.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Collections;

public class MenuUtils {

    public static void configMenu(Player player) {
        Inventory configMenu = Bukkit.createInventory(player, 9*5, "§d§lMenu §l§8>> §fClic-droit");
        Main.addProtectInventory(configMenu);

        ItemBuilder filter = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 10).setName("§7§k55555");
        ItemBuilder worldConfig = new ItemBuilder(Material.IRON_DOOR).setName("§aMonde");
        ItemBuilder back = new ItemBuilder(Material.ARROW).setName("§aSortir");

        configMenu.setItem(0, filter.toItemStack());
        configMenu.setItem(1, filter.toItemStack());
        configMenu.setItem(9, filter.toItemStack());

        configMenu.setItem(7, filter.toItemStack());
        configMenu.setItem(8, filter.toItemStack());
        configMenu.setItem(17, filter.toItemStack());

        configMenu.setItem(27, filter.toItemStack());
        configMenu.setItem(36, filter.toItemStack());
        configMenu.setItem(37, filter.toItemStack());

        configMenu.setItem(35, filter.toItemStack());
        configMenu.setItem(43, filter.toItemStack());
        configMenu.setItem(44, filter.toItemStack());

        configMenu.setItem(12, worldConfig.toItemStack());
        configMenu.setItem(40, back.toItemStack());

        player.openInventory(configMenu);
    }

    public static void mondeMenu(Player player) {
        Inventory worldMenu = Bukkit.createInventory(player, 9*5, "§a World Configuration");
        Main.addProtectInventory(worldMenu);

        ItemBuilder filter = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 10).setName("§7§k55555");
        ItemBuilder createWorld = new ItemBuilder(Material.SLIME_BALL).setName("§aCrée un monde");
        ItemBuilder deleteWorld = new ItemBuilder(Material.REDSTONE).setName("§cSupprimé le monde");
        ItemBuilder back = new ItemBuilder(Material.ARROW).setName("§aRetour");

        worldMenu.setItem(0, filter.toItemStack());
        worldMenu.setItem(1, filter.toItemStack());
        worldMenu.setItem(9, filter.toItemStack());

        worldMenu.setItem(7, filter.toItemStack());
        worldMenu.setItem(8, filter.toItemStack());
        worldMenu.setItem(17, filter.toItemStack());

        worldMenu.setItem(27, filter.toItemStack());
        worldMenu.setItem(36, filter.toItemStack());
        worldMenu.setItem(37, filter.toItemStack());

        worldMenu.setItem(35, filter.toItemStack());
        worldMenu.setItem(43, filter.toItemStack());
        worldMenu.setItem(44, filter.toItemStack());

        worldMenu.setItem(38, createWorld.toItemStack());
        worldMenu.setItem(40, back.toItemStack());
        worldMenu.setItem(42, deleteWorld.toItemStack());

        player.openInventory(worldMenu);
    }
    public static void confirmationWorldMenu(Player player) {
        Inventory confirmationWorldMenu = Bukkit.createInventory(player, 9*3, "Confirmation");
        Main.addProtectInventory(confirmationWorldMenu);

        ItemBuilder yes = new ItemBuilder(Material.EMERALD_BLOCK).setName("§aConfirmer");
        ItemBuilder no = new ItemBuilder(Material.REDSTONE_BLOCK).setName("§cRefuser");
        ItemBuilder back = new ItemBuilder(Material.ARROW).setName("§7Annuler");

        confirmationWorldMenu.setItem(11, yes.toItemStack());
        confirmationWorldMenu.setItem(15, no.toItemStack());
        confirmationWorldMenu.setItem(13, back.toItemStack());

        player.openInventory(confirmationWorldMenu);
    }
    public static void openHostInventory(Player player) {
        Inventory hostInventory = Bukkit.createInventory(null, 54, "§aListe des Hosts et Co-Hosts");
        Main.addProtectInventory(hostInventory);

        ItemStack purpleGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 10);
        ItemMeta purpleGlassMeta = purpleGlass.getItemMeta();
        if (purpleGlassMeta != null) {
            purpleGlassMeta.setDisplayName(" ");
            purpleGlass.setItemMeta(purpleGlassMeta);
        }

        hostInventory.setItem(0, purpleGlass);
        hostInventory.setItem(1, purpleGlass);
        hostInventory.setItem(9, purpleGlass);

        hostInventory.setItem(7, purpleGlass);
        hostInventory.setItem(8, purpleGlass);
        hostInventory.setItem(17, purpleGlass);

        hostInventory.setItem(36, purpleGlass);
        hostInventory.setItem(45, purpleGlass);
        hostInventory.setItem(46, purpleGlass);

        hostInventory.setItem(44, purpleGlass);
        hostInventory.setItem(52, purpleGlass);
        hostInventory.setItem(53, purpleGlass);


        int startIndex = 10;
        int endIndex = 43;
        int currentIndex = startIndex;

        for (String hostName : Main.getHosts()) {
            if (currentIndex > endIndex) break;
            ItemStack skull = createSkull(hostName, "Host", "§b");
            hostInventory.setItem(startIndex++, skull);
        }

        for (String cohostName : Main.getCohosts()) {
            if (currentIndex > endIndex) break;
            ItemStack skull = createSkull(cohostName, "Cohost", "§3");
            hostInventory.setItem(startIndex++, skull);
        }

        player.openInventory(hostInventory);
    }
    public static ItemStack createSkull(String playerName, String lore, String color) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta meta = skull.getItemMeta();
        if (meta instanceof SkullMeta) {
            SkullMeta skullMeta = (SkullMeta) meta;
            skullMeta.setOwner(playerName);
            skullMeta.setDisplayName(color + "§l• §7"+ playerName);
            skullMeta.setLore(Collections.singletonList(color + lore));
            skull.setItemMeta(skullMeta);
        }
        return skull;
    }
}
