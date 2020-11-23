/*    */ package me.Shamed.Test.commands.Tab;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.command.TabCompleter;
/*    */ 
/*    */ public class VeloTabCompleter
/*    */   implements TabCompleter {
/* 11 */   List<String> arguments = new ArrayList<String>();
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
/* 15 */     this.arguments.add("0");
/* 16 */     if (args.length <= 2)
/*    */     {
/* 18 */       return this.arguments;
/*    */     }
/* 20 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\eclipse-workspace\ShamedSMP\bin\!\me\Shamed\Test\commands\Tab\TabCompleter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */