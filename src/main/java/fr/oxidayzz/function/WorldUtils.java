package fr.oxidayzz.function;

import fr.oxidayzz.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import java.io.File;

public class WorldUtils {
    public void handleCreateWorld(Player player, String worldName) {
        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            player.sendMessage("Le monde existe déjà. Utilisez /world delete " + worldName + " pour le supprimer d'abord.");
        } else if (Main.confirmationMap.containsKey(worldName) && Main.confirmationMap.get(worldName)) {
            createWorld(worldName);
            Main.confirmationMap.remove(worldName);
            player.sendMessage("Le monde " + worldName + " a été créé.");
        } else {
            Main.confirmationMap.put(worldName, true);
            player.sendMessage("Voulez-vous vraiment créer un nouveau monde nommé " + worldName + "? Utilisez la commande à nouveau pour confirmer.");
        }
    }

    public void handleDeleteWorld(Player player, String worldName) {
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            player.sendMessage("Le monde n'existe pas.");
        } else if (Main.confirmationMap.containsKey(worldName) && Main.confirmationMap.get(worldName)) {
            deleteWorld(worldName);
            Main.confirmationMap.remove(worldName);
            player.sendMessage("Le monde " + worldName + " a été supprimé.");
        } else {
            Main.confirmationMap.put(worldName, true);
            player.sendMessage("Voulez-vous vraiment supprimer le monde nommé " + worldName + "? Utilisez la commande à nouveau pour confirmer.");
        }
    }

    private void createWorld(String worldName) {
        WorldCreator creator = new WorldCreator(worldName);
        creator.environment(World.Environment.NORMAL);
        creator.type(WorldType.NORMAL);
        Bukkit.createWorld(creator);
    }

    private void deleteWorld(String worldName) {
        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            Bukkit.unloadWorld(world, false);
            deleteWorldFiles(world.getWorldFolder());
        }
    }

    private void deleteWorldFiles(File path) {
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