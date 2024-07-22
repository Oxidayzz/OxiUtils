package fr.oxidayzz.commands;

import fr.oxidayzz.Main;
import fr.oxidayzz.function.MenuUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListHost implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("listhost")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                MenuUtils.openHostInventory(player);
                return true;
            }sender.sendMessage(Main.EPrefix + "Cette commande ne peut être exécutée que par un joueur.");
            return false;
        }
        return false;
    }
}
