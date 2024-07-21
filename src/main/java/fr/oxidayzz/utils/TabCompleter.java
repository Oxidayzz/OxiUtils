package fr.oxidayzz.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("host")) {
            if(args.length == 1) {
                return Arrays.asList("add" ,"remove");
            }else if (args.length == 2 ){
                return null;
            }
        }else if (cmd.getName().equalsIgnoreCase("cohost")) {
            if(args.length == 1) {
                return Arrays.asList("add" ,"remove");
            }else if (args.length == 2){
                return null;
            }
        }
        return null;
    }
}
