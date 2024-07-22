package fr.oxidayzz.listeners;

import fr.mrmicky.fastboard.FastBoard;
import fr.oxidayzz.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class updateBoard implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        FastBoard board = new FastBoard(player);

        board.updateTitle("FastBoard");

        Main.boards.put(player.getUniqueId(), board);
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        FastBoard board = Main.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }
}
