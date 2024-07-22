package fr.oxidayzz.commands;

import fr.oxidayzz.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListWorld implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("listworld")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(Main.Prefix + "Liste des mondes existants :");

                for(World world : Bukkit.getWorlds()) {
                    player.sendMessage("§e- §7" + world.getName());
                }

                return true;
            }else {
                sender.sendMessage(Main.EPrefix + "Cette commande ne peux etre éxécuter que par un joueur !");
            }
        }
        return false;
    }
}
