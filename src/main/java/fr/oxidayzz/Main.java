package fr.oxidayzz;

import org.bukkit.plugin.java.JavaPlugin;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    public String gitVersion;

    public static String prefix = "§d§lOxiUtils §7§l>> §7";
    public static String ePrefix = "§c§lErreur §7§l>> §c";
    public static String sPrefix = "§b§lStaff §7§l>> §c";
    private static Main instance;

    private final Logger logger = getLogger();
    public final String version = getDescription().getVersion();

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("########  ##    ##  ##  ##   ##  #######  ##  ##       #######");
        getLogger().info("##    ##   ##  ##   ##  ##   ##    ###    ##  ##       ##      ");
        getLogger().info("##    ##    ####    ##  ##   ##    ###    ##  ##       #######");
        getLogger().info("##    ##   ##  ##   ##  ##   ##    ###    ##  ##            ##");
        getLogger().info("########  ##    ##  ##  #######    ###    ##  #######  #######");
        checkVersion();
        getLogger().info("Le plugin démarre en version : " + this.version);

    }

    @Override
    public void onDisable() {

    }

    private void checkVersion() {
        try {
            String versionUrl = "https://raw.githubusercontent.com/Oxidayzz/OxiUtils/main/version";
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
                this.logger.warning("Impossible de rla version la plus ");
            }
        } catch (Exception e) {
            this.logger.warning("Erreur lors de la vde la version: " + e.getMessage());
        }
    }

}