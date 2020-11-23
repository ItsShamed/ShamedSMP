package me.Shamed.Test.listeners;

import me.Shamed.Test.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PreviewCursorInventory
  implements Listener
{
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
    final Player player = e.getPlayer();
    (new BukkitRunnable()
      {
        public void run() {
          player.getItemOnCursor();
        }
      }).runTaskTimer((Plugin)Main.getPlugin(Main.class), 0L, 2L);
  }
}