package fr.oxidayzz;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    public static String EPrefix = "§e§lUHC§6§lCore §l§8>> §c";
    public static String Prefix = "§e§lUHC§6§lCore §l§8>> §7";
    private static Main instance;

    private final Logger logger = getLogger();
    public final String version = getDescription().getVersion();

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("§o########  ##    ##  ##  ##   ##  #######  ##  ##       #######");
        getLogger().info("§o##    ##   ##  ##   ##  ##   ##    ###    ##  ##       ##      ");
        getLogger().info("§o##    ##    ####    ##  ##   ##    ###    ##  ##       #######");
        getLogger().info("§o##    ##   ##  ##   ##  ##   ##    ###    ##  ##            ##");
        getLogger().info("§o########  ##    ##  ##  #######    ###    ##  #######  #######");

        getLogger().info("Le plugin démarre en version : " + this.version);

    }

    @Override
    public void onDisable() {

    }
}