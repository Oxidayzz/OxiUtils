package fr.oxidayzz.function;

import fr.oxidayzz.Main;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class RoofedUtils {
    public static void Roofed(World world, int radius, Player player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getPlugin(Main.class), () -> nettoyage(world, radius, player), 10L);
    }

    public static void nettoyage(World world, int radius, Player player) {
        player.sendMessage(Main.Prefix + "Début du nettoyage du centre §d[1/4]");
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                world.setBiome(x, z, Biome.ROOFED_FOREST);
                for (int y = 50; y <= 100; y++) {
                    if (world.getBlockAt(x, y, z).getType().name().contains("LOG") || world.getBlockAt(x, y, z).getType().name().contains("LEAVES"))
                        world.getBlockAt(x, y, z).setType(Material.AIR);
                }
            }
        }
        player.sendMessage(Main.Prefix + "Fin du nettoyage du centre §d[2/4]");
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getPlugin(Main.class), () -> generationArbre(world, radius, radius * 7, player), 100L);
    }

    public static void generationArbre(World world, int radius, int nombreArbres, Player player) {
        player.sendMessage(Main.Prefix + "Début de la génération des arbres §d[3/4] ");
        Random random = new Random();
        for (int i = 0; i < nombreArbres; i++) {
            for (int j = 0; j < 3; j++) {
                int x = random.nextInt(radius);
                int z = random.nextInt(radius);
                int highestY = world.getHighestBlockYAt(x, z);
                int highestYNegativeZ = world.getHighestBlockYAt(x, -z);
                int highestYNegativeX = world.getHighestBlockYAt(-x, z);
                int highestYNegativeXNegativeZ = world.getHighestBlockYAt(-x, -z);
                Location location = new Location(world, x, highestY, z);
                Location locationNegativeZ = new Location(world, x, highestYNegativeZ, -z);
                Location locationNegativeX = new Location(world, -x, highestYNegativeX, z);
                Location locationNegativeXNegativeZ = new Location(world, -x, highestYNegativeXNegativeZ, -z);
                world.generateTree(location, TreeType.DARK_OAK);
                world.generateTree(locationNegativeZ, TreeType.DARK_OAK);
                world.generateTree(locationNegativeX, TreeType.DARK_OAK);
                world.generateTree(locationNegativeXNegativeZ, TreeType.DARK_OAK);
            }
        }
        player.sendMessage(Main.Prefix + "Génération terminée ! §d[4/4]");
        if (player.getWorld().getName().equals(world.getName())) {
            player.performCommand("a preview");
            player.performCommand("a preview");
        }
    }
}
