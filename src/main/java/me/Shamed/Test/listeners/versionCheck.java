package me.Shamed.Test.listeners;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import me.Shamed.Test.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.geysermc.floodgate.FloodgateAPI;
import org.geysermc.floodgate.FloodgatePlayer;
import org.geysermc.floodgate.util.DeviceOS;
import us.myles.ViaVersion.api.ViaAPI;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;




public class versionCheck
  implements Listener
{
  private ViaAPI<?> api;
  private Scoreboard OSScoreboard;
  private Map<UUID, Location> lastPlayerLocations;
  private Plugin plugin;
  
  public versionCheck(ViaAPI<?> viaAPI) {
    this.api = viaAPI;
    this.OSScoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
    this.lastPlayerLocations = ((Main)Main.getPlugin(Main.class)).LastPlayerLocations;
    this.plugin = (Plugin)Main.getPlugin(Main.class);
    
    
    
    Team JavaTeam = this.OSScoreboard.registerNewTeam("javaos");
    JavaTeam.setPrefix(ChatColor.YELLOW + "[Java]");
    String[] OsNames = { "bedrock", "android", "ios", "osx", "fireos", "gearvr", "hololens", "win10", "win", "ps4", "switch", "xboxone", "winphone" }; byte b; int i; String[] arrayOfString1;
    
    //TODO: Clean up this mess from JD-GUI
    for (i = (arrayOfString1 = OsNames).length, b = 0; b < i; ) { String name = arrayOfString1[b];
      Team team = this.OSScoreboard.registerNewTeam(name); String str1;
      switch ((str1 = name.toLowerCase()).hashCode()) { case -1274327398: if (!str1.equals("fireos")) {
            break;
          }










          
          team.setPrefix(ChatColor.translateAlternateColorCodes('&', "&l&c[&6FireOS&c]"));
          break;


        
        case -1249886069:
          if (!str1.equals("gearvr")) {
            break;
          }
          team.setPrefix(ChatColor.translateAlternateColorCodes('&', "&l&9[&fGearVR&9]"));
          break;








        
        case -1104512238:
          if (!str1.equals("winphone")) {
            break;
          }
          team.setPrefix(ChatColor.translateAlternateColorCodes('&', "&b&l[WinPhone]")); break;case -889473228: if (!str1.equals("switch"))
            break;  team.setPrefix(ChatColor.translateAlternateColorCodes('&', "&l&c[&fSwitch&c]")); break;case -861391249: if (!str1.equals("android"))
            break;  team.setPrefix(ChatColor.translateAlternateColorCodes('&', "&2&l[Android]")); break;case -440236824: if (!str1.equals("Hololens"))
            break;  team.setPrefix(ChatColor.translateAlternateColorCodes('&', "&f[&7Hololens&f]")); break;case -231550106: if (!str1.equals("bedrock"))
            break;  team.setPrefix(ChatColor.translateAlternateColorCodes('&', "&7&l[Bedrock]")); break;case 104461: if (!str1.equals("ios"))
            break;  team.setPrefix(ChatColor.translateAlternateColorCodes('&', "&l&8[&fiOS&8]")); break;case 110356: if (!str1.equals("osx"))
            break;  team.setPrefix(ChatColor.translateAlternateColorCodes('&', "&l&8[&fMac OS&8]")); break;case 111249: if (!str1.equals("ps4"))
            break;  team.setPrefix(ChatColor.translateAlternateColorCodes('&', "&l&f[&9PS4&f]")); break;case 117724: if (!str1.equals("win"))
            break;  team.setPrefix(ChatColor.translateAlternateColorCodes('&', "&9[&bWindows&9]")); break;case 113134331: if (!str1.equals("win10"))
            break;  team.setPrefix(ChatColor.translateAlternateColorCodes('&', "&l&3[&bWin10&3]")); break;
        case 2038112051: if (!str1.equals("xboxone"))
            break;  team.setPrefix(ChatColor.translateAlternateColorCodes('&', "&l&2[&fXbox&2]")); break; }  b++; }  } @EventHandler public void onPlayerJoin(PlayerJoinEvent e) { final Player player = e.getPlayer();
    this.plugin.getLogger().info(String.format("[Shamed] %1$s joined in %2$s", new Object[] { player.getName(), ProtocolVersion.getProtocol(this.api.getPlayerVersion(player.getUniqueId())).toString() }));
    if (this.api.getPlayerVersion(player.getUniqueId()) >= 735) {
      
      player.setGameMode(GameMode.SURVIVAL);
      
      if (FloodgateAPI.isBedrockPlayer(player)) {
        FloodgatePlayer bedrockPlayer = FloodgateAPI.getPlayer(player);
        DeviceOS bedrockOS = bedrockPlayer.getDeviceOS();
        this.plugin.getLogger().info(String.format("[Shamed] %1$s joined with Minecraft: Bedrock Edition on %2$s", new Object[] { player.getName(), bedrockOS.toString() }));
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&l&bWelcome! &c&lâ�¤"), ChatColor.translateAlternateColorCodes('&', "&dSpecial " + bedrockOS.toString() + "!"), 5, 40, 10);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dYou are using Minecraft: Bedrock Edition on " + bedrockOS.toString() + "."));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dPlease know that there is still some bugs out there with the Bedrock Edition, but it's playable."));
        switch (bedrockOS) {
          
          case UNKNOWN:
          case DEDICATED:
            this.OSScoreboard.getTeam("bedrock").addEntry(player.getName());
            break;
          
          case ANDROID:
            this.OSScoreboard.getTeam("android").addEntry(player.getName());
            break;
          
          case IOS:
            this.OSScoreboard.getTeam("ios").addEntry(player.getName());
            break;
          
          case OSX:
            this.OSScoreboard.getTeam("osx").addEntry(player.getName());
            break;
          
          case FIREOS:
            this.OSScoreboard.getTeam("fireos").addEntry(player.getName());
            break;
          
          case GEARVR:
            this.OSScoreboard.getTeam("gearvr").addEntry(player.getName());
            break;
          
          case HOLOLENS:
            this.OSScoreboard.getTeam("hololens").addEntry(player.getName());
            break;
          
          case WIN10:
            this.OSScoreboard.getTeam("win10").addEntry(player.getName());
            break;
          
          case WIN32:
            this.OSScoreboard.getTeam("win").addEntry(player.getName());
            break;
          
          case ORBIS:
            this.OSScoreboard.getTeam("ps4").addEntry(player.getName());
            break;
          
          case NX:
          case SWITCH:
            this.OSScoreboard.getTeam("switch").addEntry(player.getName());
            break;
          
          case XBOX_ONE:
            this.OSScoreboard.getTeam("xboxone").addEntry(player.getName());
            break;
          
          case WIN_PHONE:
            this.OSScoreboard.getTeam("winphone").addEntry(player.getName());
            break;
        } 




      
      } else {
        player.setGameMode(GameMode.SURVIVAL);
        if (this.lastPlayerLocations.containsKey(player.getUniqueId())) {
          player.teleport(this.lastPlayerLocations.get(player.getUniqueId()));
        } else {
          
          player.teleport(player.getWorld().getSpawnLocation());
        } 
        this.plugin.getLogger().fine(String.format("[Shamed] Teleported %s to its last location or spawn.", new Object[] { player.getName() }));
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&l&bWelcome! &c&lâ�¤"), ChatColor.translateAlternateColorCodes('&', "&aEnjoy the SMP!"), 5, 40, 10);
        player.sendMessage(ChatColor.GREEN + "You are using the perfect version for the SMP! Enjoy!");
        this.OSScoreboard.getTeam("javaos").addEntry(player.getName());
      
      }
    
    }
    else if (this.api.getPlayerVersion(player.getUniqueId()) >= 393) {
      player.setGameMode(GameMode.SURVIVAL);
      if (this.lastPlayerLocations.containsKey(player.getUniqueId())) {
        player.teleport(this.lastPlayerLocations.get(player.getUniqueId()));
      } else {
        
        player.teleport(player.getWorld().getSpawnLocation());
      } 
      this.plugin.getLogger().fine(String.format("[Shamed] Teleported %s to its last location or spawn.", new Object[] { player.getName() }));
      player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&l&bWelcome! &c&lâ�¤"), ChatColor.GOLD + "Okay version!", 5, 40, 10);
      player.sendMessage(ChatColor.GOLD + String.format("The version you are running (%s) is okay. Some blocks may appear weird because of incompatibility.", new Object[] { ProtocolVersion.getProtocol(this.api.getPlayerVersion(player.getUniqueId())).toString() }));
      this.OSScoreboard.getTeam("java").addEntry(player.getName());
    }
    else {
      
      player.setGameMode(GameMode.SPECTATOR);
      if (this.lastPlayerLocations.containsKey(player.getUniqueId())) {
        player.teleport(this.lastPlayerLocations.get(player.getUniqueId()));
      } else {
        
        player.teleport(player.getWorld().getSpawnLocation());
      } 
      this.plugin.getLogger().fine(String.format("[Shamed] Teleported %s to its last location or spawn.", new Object[] { player.getName() }));
      Bukkit.getLogger().log(Level.INFO, "Unsupported version.");
      player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&l&bWelcome! &c&lâ�¤"), ChatColor.RED + "Unsupported version", 5, 40, 10);
      player.sendMessage(ChatColor.RED + String.format("Your version (%s) is unsupported. Thus, you have been put in spectator mode.", new Object[] { ProtocolVersion.getProtocol(this.api.getPlayerVersion(player.getUniqueId())).toString() }));
      this.OSScoreboard.getTeam("javaos").addEntry(player.getName());
      
      final Hologram hologram = HologramsAPI.createHologram((Plugin)JavaPlugin.getPlugin(Main.class), player.getLocation().add(0.0D, 2.75D, 0.0D));
      hologram.appendTextLine(ChatColor.RED + String.format("Unsupported (%s)", new Object[] { ProtocolVersion.getProtocol(this.api.getPlayerVersion(player.getUniqueId())).toString() }));
      hologram.appendTextLine(String.format("%1$s %2$s", new Object[] { this.OSScoreboard.getEntryTeam(player.getName()).getPrefix(), player.getDisplayName() }));

      
      (new BukkitRunnable()
        {
          public void run() {
            player.setGameMode(GameMode.SPECTATOR);
            hologram.teleport(player.getLocation().add(0.0D, 2.75D, 0.0D));
            
            player.getSpectatorTarget();


            
            if (player.isDead() || !player.isOnline()) {
              hologram.delete();
              cancel();
            
            }
          
          }
        }).runTaskTimer((Plugin)Main.getPlugin(Main.class), 0L, 1L);
    }  }

  
  @EventHandler
  public void onPlayerLeave(PlayerQuitEvent e) {
    Player player = e.getPlayer();
    this.OSScoreboard.getEntryTeam(e.getPlayer().getName()).removeEntry(e.getPlayer().getName());
    if (this.api.getPlayerVersion(player.getUniqueId()) >= 393)
      if (this.lastPlayerLocations.containsKey(player.getUniqueId())) {
        this.lastPlayerLocations.replace(player.getUniqueId(), player.getLocation());
      } else {
        
        this.lastPlayerLocations.put(player.getUniqueId(), player.getLocation());
      }  
  }
}