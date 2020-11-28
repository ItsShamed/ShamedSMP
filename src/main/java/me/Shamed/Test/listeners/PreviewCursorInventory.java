package me.Shamed.Test.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;

import me.Shamed.Test.Main;

public class PreviewCursorInventory implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		final Player player = e.getPlayer();
		final Hologram hologram = HologramsAPI.createHologram(Main.getPlugin(Main.class), player.getLocation().add(0, 3, 0));
		ItemLine itemLine = hologram.appendItemLine((player.getItemOnCursor()==null) ? (new ItemStack(Material.AIR)) : (player.getItemOnCursor()));
		new BukkitRunnable() {
			public void run() {
				hologram.teleport(player.getLocation().add(0, 3, 0));
				itemLine.setItemStack((player.getItemOnCursor()==null) ? (new ItemStack(Material.AIR)) : (player.getItemOnCursor()));
			}
		}.runTaskTimer(Main.getPlugin(Main.class), 0L, 2L);
	}
}