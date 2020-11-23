 package me.Shamed.Test.listeners;
 
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
 
 public class veloRod
   implements Listener {
   @EventHandler
   public void onLaunch(PlayerInteractEvent e) {
     if (e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD) && 
       e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore() && 
       e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Velocity / KB Fireball")) {
       if (e.getAction() == Action.RIGHT_CLICK_AIR) {
         if (e.getPlayer().hasPermission("shamed.velocity.rod") && 
           e.getPlayer().getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR) {
           e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_GHAST_SHOOT, 1.0F, 1.0F);
           e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(6).setY(1.5D));
           e.getPlayer().setAllowFlight(true);
         } 
       } else if (e.getAction() == Action.RIGHT_CLICK_BLOCK && 
         e.getPlayer().hasPermission("shamed.velocity.rod") && 
         e.getPlayer().getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR && 
         e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
         e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_GHAST_SHOOT, 1.0F, 1.0F);
         e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(4).setY(2));
         e.getPlayer().setAllowFlight(true);
       } 
     }
   }
 
 
   
   @EventHandler
   public void onFall(EntityDamageEvent e) {
     if (e.getEntity() instanceof Player) {
       Player p = (Player)e.getEntity();
       if (p.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD) && 
         p.getInventory().getItemInMainHand().getItemMeta().hasLore() && 
         p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Velocity / KB Fireball") && 
         p.hasPermission("shamed.velocity.rod"))
         if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
           p.getWorld().createExplosion(p.getLocation(), 0.0F);
           p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.125F, 0.5F);
           p.setVelocity(p.getLocation().getDirection().multiply(1.5D).setY(0.5D));
           e.setCancelled(true);
         }
         else if (e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
           e.setCancelled(true);
         }  
     } 
   }
 }
