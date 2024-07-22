package fr.oxidayzz.function;

import fr.oxidayzz.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import java.io.File;

public class WorldUtils {
    public static void createWorld(Player player, String worldName) {
        player.sendMessage(Main.Prefix + "Création du monde en cours ...");
        WorldCreator creator = new WorldCreator(worldName);
        creator.environment(World.Environment.NORMAL);
        creator.type(WorldType.NORMAL);
        Bukkit.createWorld(creator);
        player.sendMessage(Main.Prefix + "Création du monde terminé !");
    }

    public static void deleteWorld(Player player, String worldName) {
        World world = Bukkit.getWorld(worldName);
        player.sendMessage(Main.Prefix + "Suppression du monde en cours ...");
        if (world != null) {
            Bukkit.unloadWorld(world, false);
            deleteWorldFiles(world.getWorldFolder());
            player.sendMessage(Main.Prefix + "La suppresion du monde a été effectuer avec succés !");
        }
    }

    private static void deleteWorldFiles(File path) {
        if (path.exists()) {
            for (File file : path.listFiles()) {
                if (file.isDirectory()) {
                    deleteWorldFiles(file);
                } else {
                    file.delete();
                }
            }
            path.delete();
        }
    }
}