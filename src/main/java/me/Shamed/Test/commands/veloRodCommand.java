/*    */ package me.Shamed.Test.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ public class veloRodCommand
/*    */   implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
/* 19 */     if (l.equalsIgnoreCase("velorod")) {
/* 20 */       if (!(s instanceof Player)) {
/* 21 */         s.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6This command can't be used from the console."));
/* 22 */         return true;
/*    */       } 
/* 24 */       Player p = (Player)s;
/* 25 */       if (!p.hasPermission("shamed.velocity.rod")) {
/* 26 */         p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lYou do not have permission to use this command."));
/* 27 */         return true;
/*    */       } 
/* 29 */       if (p.getInventory().firstEmpty() == -1) {
/* 30 */         p.getWorld().dropItemNaturally(p.getLocation(), i());
/* 31 */         return true;
/*    */       } 
/* 33 */       p.getInventory().addItem(new ItemStack[] { i() });
/* 34 */       return true;
/*    */     } 
/* 36 */     return false;
/*    */   }
/*    */   
/*    */   public ItemStack i() {
/* 40 */     ItemStack fb = new ItemStack(Material.BLAZE_ROD);
/* 41 */     ItemMeta meta = fb.getItemMeta();
/* 42 */     meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&d&lVelocity / KB Fireball"));
/* 43 */     List<String> lo = new ArrayList<String>();
/* 44 */     lo.add("");
/* 45 */     lo.add(ChatColor.translateAlternateColorCodes('&', "&7&oItem #0001"));
/* 46 */     meta.setLore(lo);
/* 47 */     fb.setItemMeta(meta);
/* 48 */     return fb;
/*    */   }
/*    */ }


/* Location:              D:\eclipse-workspace\ShamedSMP\bin\!\me\Shamed\Test\commands\veloRodCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */