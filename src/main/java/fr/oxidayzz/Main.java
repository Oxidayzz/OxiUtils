package fr.oxidayzz;

import fr.mrmicky.fastboard.FastBoard;
import fr.oxidayzz.commands.GestionCohost;
import fr.oxidayzz.commands.GestionHost;
import fr.oxidayzz.commands.ListHost;
import fr.oxidayzz.commands.ListWorld;
import fr.oxidayzz.listeners.onClick;
import fr.oxidayzz.listeners.protectedInventory;
import fr.oxidayzz.listeners.updateBoard;
import fr.oxidayzz.utils.ItemBuilder;
import fr.oxidayzz.utils.TabCompleter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    public String gitVersion;

    public static ArrayList<String> hosts;
    public static ArrayList<String> cohosts;
    public static ArrayList<Inventory> protectedInventory = new ArrayList<>();
    public static Map<String, Boolean> confirmationMap = new HashMap<>();
    public static String EPrefix = "§e§lUHC§6§lCore §l§8>> §c";
    public static String Prefix = "§e§lUHC§6§lCore §l§8>> §7";
    public static final Map<UUID, FastBoard> boards = new HashMap<>();
    private static Main instance;

    private final Logger logger = getLogger();
    public final String version = getDescription().getVersion();

    public static List<String> getHosts() {
        return hosts;
    }
    public static List<String> getCohosts() {
        return cohosts;
    }

    @Override
    public void onEnable() {
        checkVersion();
        instance = this;
        hosts = new ArrayList<>();
        cohosts = new ArrayList<>();
        getLogger().info("§o########  ##    ##  ##  ##   ##  #######  ##  ##       #######");
        getLogger().info("§o##    ##   ##  ##   ##  ##   ##    ###    ##  ##       ##      ");
        getLogger().info("§o##    ##    ####    ##  ##   ##    ###    ##  ##       #######");
        getLogger().info("§o##    ##   ##  ##   ##  ##   ##    ###    ##  ##            ##");
        getLogger().info("§o########  ##    ##  ##  #######    ###    ##  #######  #######");

        getLogger().info("Le plugin démarre en version : " + this.version);

        this.getCommand("host").setExecutor(new GestionHost());
        this.getCommand("cohost").setExecutor(new GestionCohost());
        this.getCommand("listworld").setExecutor(new ListWorld());
        this.getCommand("listhost").setExecutor(new ListHost());

        this.getCommand("host").setTabCompleter(new TabCompleter());
        this.getCommand("cohost").setTabCompleter(new TabCompleter());

        getServer().getPluginManager().registerEvents(new onClick(), this);
        getServer().getPluginManager().registerEvents(new updateBoard(), this);
        getServer().getPluginManager().registerEvents(new protectedInventory(), this);
        new BukkitRunnable() {

            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    updatePlayerPrefix(player);
                    hostGiveItem(player);
                    for (FastBoard board : boards.values()) {
                        updateBoard(board);
                    }
                }
            }
        }.runTaskTimerAsynchronously(this, 0, 20);
    }

    @Override
    public void onDisable() {

    }
    private void checkVersion() {
        try {
            String versionUrl = "https://raw.githubusercontent.com/Oxidayzz/OxiUtils/main/src/main/resources/version";
            HttpURLConnection connection = (HttpURLConnection)(new URL(versionUrl)).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String latestVersion = in.readLine();
            in.close();
            if (latestVersion != null && !latestVersion.isEmpty()) {
                this.gitVersion = latestVersion;
                if (this.version.equals(latestVersion.trim())) {
                    this.logger.info("Votre plugin est jour.");
                } else {
                    this.logger.info("+---------------------------------------+");
                    this.logger.info("Une nouvelle version est disponible: " + latestVersion);
                    this.logger.info("+---------------------------------------+");
                }
            } else {
                this.logger.warning("Impossible de récupéré la version la plus récente ");
            }
        } catch (Exception e) {
            this.logger.warning("Erreur lors de la vérification de la version: " + e.getMessage());
        }
    }
    private void hostGiveItem(Player player) {
        String playerName = player.getName();
        if(Main.hosts.contains(playerName) || Main.cohosts.contains(playerName)) {
            ItemBuilder menu = new ItemBuilder(Material.COMPASS).setName("§d§lMenu §l§8>> §fClic-droit");
            player.getInventory().setItem(8, menu.toItemStack());
        }else {
            removeCustomItem(player, "§d§lMenu §l§8>> §fClic-droit");
        }
    }
    private void removeCustomItem(Player player, String itemName) {
        ItemStack[] contents = player.getInventory().getContents();

        for (ItemStack item : contents) {
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(itemName)) {
                player.getInventory().remove(item);
                break;
            }
        }
        player.updateInventory();
    }
    private void updatePlayerPrefix(Player player) {
        String playerName = player.getName();
        if(hosts.contains(playerName)) {
            String prefix = "§b✦ §r";
            player.setPlayerListName(prefix + playerName);
            player.setDisplayName(prefix + playerName);
        }else if (cohosts.contains(playerName)) {
            String prefix = "§3✦ §r";
            player.setPlayerListName(prefix + player.getName());
            player.setDisplayName(prefix + player.getName());
        }else if (!hosts.contains(playerName) && !cohosts.contains(playerName)) {
            String prefix = "";
            player.setDisplayName(player.getName());
            player.setDisplayName(player.getName());
        }
    }
    private  void updateBoard(FastBoard board){
        board.updateLines(
                "",
                "Players: " + getServer().getOnlinePlayers().size(),
                "",
                "Kills: " + board.getPlayer().getStatistic(Statistic.PLAYER_KILLS),
                ""
        );
    }
    public static void addProtectInventory(Inventory inventory) {
        protectedInventory.add(inventory);
    }
    public static ArrayList<Inventory> getProtectedInventory() {
        return protectedInventory;
    }
}