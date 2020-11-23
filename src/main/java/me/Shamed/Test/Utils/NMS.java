package me.Shamed.Test.Utils;

import org.bukkit.Bukkit;

public class NMS {
  public static Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException {
    return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
  }
}
