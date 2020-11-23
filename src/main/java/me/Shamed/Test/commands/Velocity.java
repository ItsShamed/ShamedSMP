/*    */ package me.Shamed.Test.commands;
/*    */ 
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class Velocity
/*    */   implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
/* 14 */     if (l.equalsIgnoreCase("velocity")) {
/* 15 */       double multiplier, sety; if (!(s instanceof Player)) {
/* 16 */         s.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You can't perform this command in console."));
/* 17 */         return true;
/*    */       } 
/*    */       
/* 20 */       Player p = (Player)s;
/* 21 */       if (a.length < 1) {
/* 22 */         p.setVelocity(p.getLocation().getDirection().multiply(2).setY(2));
/* 23 */         p.sendMessage("Velocity set to multiply 2 and Y 2.");
/* 24 */         return true;
/*    */       } 
/* 26 */       if (a.length == 1) {
/* 27 */         if (!isNaN(a[0])) {
/* 28 */           p.setVelocity(p.getLocation().getDirection().multiply(Integer.parseInt(a[0])).setY(2));
/* 29 */           p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Velocity set to multiply &6" + a[0] + "&r and Y 2."));
/* 30 */           return true;
/*    */         } 
/*    */         
/* 33 */         p.setVelocity(p.getLocation().getDirection().multiply(2).setY(2));
/* 34 */         p.sendMessage("Velocity set to multiply 2 and Y 2.");
/* 35 */         return true;
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 41 */       if (!isNaN(a[0])) {
/* 42 */         multiplier = Integer.parseInt(a[0]);
/*    */       } else {
/*    */         
/* 45 */         multiplier = 2.0D;
/*    */       } 
/* 47 */       if (!isNaN(a[1])) {
/* 48 */         sety = Integer.parseInt(a[1]);
/*    */       } else {
/*    */         
/* 51 */         sety = 2.0D;
/*    */       } 
/* 53 */       p.setVelocity(p.getLocation().getDirection().multiply(multiplier).setY(sety));
/* 54 */       p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Velocity set to multiply &6" + multiplier + "&r and Y &6" + sety + "&r."));
/* 55 */       return true;
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 61 */     return false;
/*    */   }
/*    */   
/*    */   public boolean isNaN(String num) {
/*    */     try {
/* 66 */       Integer.parseInt(num);
/* 67 */       return false;
/*    */     }
/* 69 */     catch (Exception e) {
/* 70 */       return true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\eclipse-workspace\ShamedSMP\bin\!\me\Shamed\Test\commands\Velocity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */