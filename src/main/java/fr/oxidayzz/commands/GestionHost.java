package fr.oxidayzz.commands;

import fr.oxidayzz.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GestionHost implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(Main.EPrefix + "Usage: /host " + label + " <player>");
            return false;
        }
        if(args[0].equalsIgnoreCase("add")) {
            String targetPlayer =  args[1];
            if(Bukkit.getPlayer(targetPlayer) != null) {
                Player target = Bukkit.getPlayer(targetPlayer);
                if(!Main.hosts.contains(targetPlayer)) {
                    Main.hosts.add(targetPlayer);
                    sender.sendMessage(Main.Prefix + "§e" + targetPlayer + "§7 a bien été ajouté en tant que host !");
                    target.sendMessage(Main.Prefix + "Vous avez été ajoutez en tant que host par §e" + sender.getName() + "§7 !!" );
                } else {
                    sender.sendMessage(Main.EPrefix + "Le joueur §e" + targetPlayer + "§c est deja host !!");
                }
            }else {
                sender.sendMessage(Main.EPrefix + "Le joueur §e" + targetPlayer + "§c n'existe pas ou n'est pas en ligne !");
            }
        }else if (args[0].equalsIgnoreCase("remove")) {
            String targetPlayer = args[1];
            if (Bukkit.getPlayer(targetPlayer) != null) {
                Player target = Bukkit.getPlayer(targetPlayer);
                if (Main.hosts.contains(targetPlayer)) {
                    Main.hosts.remove(targetPlayer);
                    sender.sendMessage(Main.Prefix + "§e" + targetPlayer + "§7 a bien été retiré en tant que host !");
                    target.sendMessage(Main.Prefix + "Vous avez été retirez de la liste des hosts par §e" + sender.getName() + "§7 !!");
                } else {
                    sender.sendMessage(Main.EPrefix + "Le joueur §e" + targetPlayer + "§c n'est pas host !!");
                }
            } else {
                sender.sendMessage(Main.EPrefix + "Le joueur §e" + targetPlayer + "§c n'existe pas ou n'est pas en ligne !");

            }
        }
        return true;
    }
}