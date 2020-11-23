 package me.Shamed.Test.Runnables;
 
 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.entity.Player;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.scheduler.BukkitRunnable;
 import org.bukkit.scoreboard.DisplaySlot;
 import org.bukkit.scoreboard.Objective;
 import org.bukkit.scoreboard.Scoreboard;
 import org.bukkit.scoreboard.ScoreboardManager;
 
 
 
 public class showHealth
 {
   public static void run(Plugin plugin) {
     ScoreboardManager m = Bukkit.getScoreboardManager();
     final Scoreboard s = m.getNewScoreboard();
     Objective o = s.registerNewObjective("showhealth", "health", ChatColor.RED + "â�¤");
     o.setDisplaySlot(DisplaySlot.BELOW_NAME);
     Objective os = s.registerNewObjective("showhealthList", "health", ChatColor.RED + "â�¤");
     os.setDisplaySlot(DisplaySlot.PLAYER_LIST);
     
     (new BukkitRunnable()
       {
         public void run()
         {
           for (Player p : Bukkit.getOnlinePlayers()) {
             
             p.setScoreboard(s);
             if (!p.isDead()) {
               p.setHealth(p.getHealth());
             }
           } 
         }
       }).runTaskTimer(plugin, 0L, 1L);
   }
 }