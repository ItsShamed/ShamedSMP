package me.Shamed.Test.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;
import java.util.HashMap;
import java.util.Map;
import me.Shamed.Test.Utils.PacketWrapper.WrapperPlayClientSteerVehicle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;



public class RidablePlayers
  implements Listener
{
  private Plugin plugin;
  private ProtocolManager manager = ProtocolLibrary.getProtocolManager();
  private Map<String, ArmorStand> ridedPlayers = new HashMap<String, ArmorStand>();
  private Map<String, Player> rideCouple = new HashMap<String, Player>();
  private Map<String, Player> invRideCouple = new HashMap<String, Player>();

  
  public RidablePlayers(Plugin plugin) {
    this.plugin = plugin;
    registerPacketListeners(plugin);
  }

  
  @EventHandler
  public void onRightClick(PlayerInteractAtEntityEvent e) {
    if (e.getRightClicked() instanceof Player) {

      
      Player mount = (Player)e.getRightClicked();
      Player rider = e.getPlayer();
      
      if (mount.hasPermission("shamed.ridable")) {


        
        if (this.ridedPlayers.containsKey(rider.getUniqueId().toString()))
        {
          
          if (mount == this.invRideCouple.get(rider.getUniqueId().toString())) {






            
            ((ArmorStand)this.ridedPlayers.get(rider.getUniqueId().toString())).eject();
            
            ride(rider, mount);

            
            return;
          } 
        }
        
        if (!isTop(mount) && 
          !this.ridedPlayers.containsKey(mount.getUniqueId().toString())) {
          Player final_mount = getTop(mount);
          ride(rider, final_mount);

          
          return;
        } 

        
        if (this.rideCouple.containsKey(mount.getUniqueId().toString())) {
          e.setCancelled(true);
          
          return;
        } 
        ride(rider, mount);
        return;
      } 
    } 
  }





  
  @EventHandler
  public void onPlayerMove(PlayerMoveEvent e) {
    if (this.ridedPlayers.containsKey(e.getPlayer().getUniqueId().toString())) {
      if (e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) {
        e.getPlayer().hidePlayer(this.plugin, this.invRideCouple.get(e.getPlayer().getUniqueId().toString()));
      } else {
        
        e.getPlayer().showPlayer(this.plugin, this.invRideCouple.get(e.getPlayer().getUniqueId().toString()));
      } 
      net.minecraft.server.v1_16_R3.Entity nmsEntity = ((CraftEntity)this.ridedPlayers.get(e.getPlayer().getUniqueId().toString())).getHandle();
      nmsEntity.setPositionRotation(e.getPlayer().getLocation().getX(), e.getPlayer().getLocation().getY() + 2.65D, e.getPlayer().getLocation().getZ(), e.getPlayer().getLocation().getYaw(), e.getPlayer().getLocation().getPitch());







      
      System.out.println("RIDABLE[HASHMAP]>> " + this.ridedPlayers.toString());
      System.out.println("RIDABLE[AM]>> " + ((ArmorStand)this.ridedPlayers.get(e.getPlayer().getUniqueId().toString())).toString());
    } 
  }




  
  @EventHandler
  public void onSuffocate(EntityDamageEvent e) {
    if (e.getEntity() instanceof Player) {
      Player rider = (Player)e.getEntity();
      if (this.rideCouple.containsKey(rider.getUniqueId().toString()) && 
        e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
        e.setCancelled(true);
      }
    } 
  }


  
  @EventHandler
  public void onHit(EntityDamageByEntityEvent e) {
    if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
      
      Player damager = (Player)e.getDamager();
      
      if (this.rideCouple.containsKey(damager.getUniqueId().toString()) || this.invRideCouple.containsKey(damager.getUniqueId().toString())) {
        e.setCancelled(true);
      }
    } 
  }



  
  private void registerPacketListeners(Plugin plugin) {
    this.manager.addPacketListener(
        (PacketListener)new PacketAdapter(plugin, ListenerPriority.NORMAL, new PacketType[] { PacketType.Play.Client.STEER_VEHICLE })
        {
          
          @SuppressWarnings("deprecation")
public void onPacketReceiving(PacketEvent e)
          {
            Player p = e.getPlayer();
            WrapperPlayClientSteerVehicle packet = new WrapperPlayClientSteerVehicle(e.getPacket());
            
            if (RidablePlayers.this.rideCouple.containsKey(p.getUniqueId().toString())) {
              Player mount = (Player)RidablePlayers.this.rideCouple.get(p.getUniqueId().toString());
              if (!RidablePlayers.this.rideCouple.containsKey(mount.getUniqueId().toString())) {
                if (mount.hasPermission(new Permission("shamed.ridable.controllable")))
                  if (packet.getForward() > 0.0F) {
                    mount.setVelocity(p.getLocation().getDirection().setY(0));
                  }
                  else if (packet.getForward() < 0.0F) {
                    mount.setVelocity(p.getLocation().getDirection().multiply(-1).setY(0));
                  }  
                if (packet.isJump()) {
                  if (mount.hasPotionEffect(PotionEffectType.JUMP)) {
                    for (PotionEffect effect : mount.getActivePotionEffects()) {
                      if (effect.getType() == PotionEffectType.JUMP && 
                        mount.isOnGround()) {
                        mount.setVelocity(mount.getVelocity().setY((effect.getAmplifier() + 1) * 0.1D));
                      }
                    }
                  
                  }
                  else if (mount.isOnGround()) {
                    mount.setVelocity(mount.getVelocity().setY(0.42D));
                  } 
                }
              } 
            } 
          }
        });
  }

  
  private boolean isTop(Player player) {
    if (this.rideCouple.containsKey(player.getUniqueId().toString())) {
      return false;
    }
    
    return true;
  }

  
  private Player getTop(Player player) {
    if (!isTop(player)) {
      return getTop(this.invRideCouple.get(player.getUniqueId().toString()));
    }
    return player;
  }


  
  private static int getDistance(Entity e) {
    Location loc = e.getLocation().clone();
    double y = loc.getBlockY();
    int distance = 0;
    for (double i = y; i >= 0.0D; i--) {
      loc.setY(i);
      if (loc.getBlock().getType().isSolid())
        break;  distance++;
    } 
    return distance;
  }
  
  private void ride(final Player passenger, final Player mount) {
    Location spawnLocation = mount.getLocation();
    spawnLocation.setY(spawnLocation.getY() + 2.65D);
    
    final ArmorStand am = (ArmorStand)mount.getWorld().spawn(spawnLocation, ArmorStand.class);

    
    am.setVisible(false);
    am.setSmall(true);
    am.setMarker(true);
    am.setGravity(false);
    am.addPassenger(passenger);
    
    this.ridedPlayers.put(mount.getUniqueId().toString(), am);
    this.invRideCouple.put(mount.getUniqueId().toString(), passenger);
    this.rideCouple.put(passenger.getUniqueId().toString(), mount);



    
    System.out.println("RIDABLE[NEW]>> " + this.ridedPlayers.toString());

    
    (new BukkitRunnable()
      {
        
        public void run()
        {
          am.setHealth(mount.getHealth());
          
          if (mount.getInventory().getItemInMainHand().getType().isBlock() || mount.getInventory().getItemInOffHand().getType().isBlock()) {
            mount.hidePlayer(RidablePlayers.this.plugin, passenger);
          } else {
            
            mount.showPlayer(RidablePlayers.this.plugin, passenger);
          } 
          
          if (am.getPassengers().get(0) == null || mount.isDead() || ((Entity)am.getPassengers().get(0)).isDead()) {
            
            RidablePlayers.this.ridedPlayers.remove(mount.getUniqueId().toString());
            RidablePlayers.this.invRideCouple.remove(mount.getUniqueId().toString());
            RidablePlayers.this.rideCouple.remove(passenger.getUniqueId().toString());

            
            passenger.showPlayer(RidablePlayers.this.plugin, mount);
            
            am.remove();
            
            passenger.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, (int)(10.0D * Math.abs(RidablePlayers.getDistance((Entity)passenger)) * 0.25D + 2.0D), 2));

            
            System.out.println("RIDABLE[REMOVED]>> " + RidablePlayers.this.ridedPlayers.toString());
            
            cancel();

            
            return;
          } 
        }
      }).runTaskTimer(this.plugin, 0L, 1L);
  }
}
