package me.Shamed.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import me.Shamed.Test.Runnables.showHealth;
import me.Shamed.Test.commands.Velocity;
import me.Shamed.Test.commands.veloRodCommand;
import me.Shamed.Test.commands.Tab.VeloTabCompleter;
import me.Shamed.Test.listeners.RidablePlayers;
import me.Shamed.Test.listeners.veloRod;
import me.Shamed.Test.listeners.versionCheck;
import us.myles.ViaVersion.api.Via;

public class Main extends JavaPlugin {
	public Map<UUID, Location> LastPlayerLocations;

	public Map<UUID, Location> getLastPlayerLocations() {
		return this.LastPlayerLocations;
	}

	public void onEnable() {
		getLogger().setFilter(new Filter() {
			public boolean isLoggable(LogRecord record) {
				record.setLevel(Level.ALL);
				return true;
			}
		});
		loadPlayerLocations();
		getCommand("Velocity").setExecutor(new Velocity());
		getCommand("Velocity").setTabCompleter((VeloTabCompleter) new VeloTabCompleter());
		getCommand("VeloRod").setExecutor(new veloRodCommand());
		getServer().getPluginManager().registerEvents(new veloRod(),this);
		getServer().getPluginManager().registerEvents(new RidablePlayers(this),this);
		showHealth.run(this);
		getServer().getPluginManager().registerEvents(new versionCheck(Via.getAPI()),this);
	}

	public void onDisable() {
		savePlayerLocations();
	}

	@SuppressWarnings("unchecked")
	private void loadPlayerLocations() {
		try {
			if (!getDataFolder().exists()) {
				getLogger().config("[Shamed] Data folder does not exist! Creating one...");
				getDataFolder().mkdir();
				getLogger().fine("[Shamed] Data folder created!");
			}

			getLogger().info("[Shamed] Loading player locations...");
			getLogger().config("[Shamed] Loading locations.map");
			FileInputStream fileIn = new FileInputStream(getDataFolder() + File.separator + "locations.map");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			getLogger().config("[Shamed] Gathering HashMap<UUID, Location> object...");
			this.LastPlayerLocations = (HashMap<UUID, Location>) in.readObject();
			getLogger().config("[Shamed] Closing file...");
			in.close();
			fileIn.close();
			getLogger().fine("[Shamed] Player locations loaded!");
		} catch (Exception e) {
			try {
				getLogger().info("[Shamed] locations.map does not exists, instantating one...");
				getLogger().config("[Shamed] Creating locations.map");
				FileOutputStream fileOut = new FileOutputStream(getDataFolder() + File.separator + "locations.map");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				getLogger().config("[Shamed] Creating new HashMap<UUID, Location> object...");
				this.LastPlayerLocations = new HashMap<UUID, Location>();
				getLogger().config("[Shamed] Writing object into file...");
				out.writeObject(this.LastPlayerLocations);
				getLogger().config("[Shamed] Closing file...");
				out.close();
				fileOut.close();
				getLogger().fine("[Shamed] location.map created!");
			} catch (IOException ex) {
				byte b;
				int i;
				StackTraceElement[] arrayOfStackTraceElement;
				for (i = (arrayOfStackTraceElement = ex.getStackTrace()).length, b = 0; b < i;) {
					StackTraceElement stack = arrayOfStackTraceElement[b];
					getLogger().severe("[Shamed] " + stack.toString());
					b++;
				}

			}
		}
	}

	private void savePlayerLocations() {
		try {
			getLogger().info("[Shamed] Saving player locations...");
			getLogger().config("[Shamed] Opening locations.map");
			FileOutputStream fileOut = new FileOutputStream(getDataFolder() + File.separator + "locations.map");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			getLogger().config("[Shamed] Writing object into file...");
			out.writeObject(this.LastPlayerLocations);
			getLogger().config("[Shamed] Closing file...");
			out.close();
			fileOut.close();
			getLogger().fine("[Shamed] location.map created!");
		} catch (IOException ex) {
			byte b;
			int i;
			StackTraceElement[] arrayOfStackTraceElement;
			for (i = (arrayOfStackTraceElement = ex.getStackTrace()).length, b = 0; b < i;) {
				StackTraceElement stack = arrayOfStackTraceElement[b];
				getLogger().severe("[Shamed] " + stack.toString());
				b++;
			}

		}
	}
}