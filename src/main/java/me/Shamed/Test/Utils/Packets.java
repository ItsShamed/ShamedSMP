package me.Shamed.Test.Utils;

import java.lang.reflect.InvocationTargetException;
import org.bukkit.entity.Player;

public class Packets
{
  public static void sendPacket(Player p, Object packet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException {
    Object nmsPlayer = p.getClass().getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);
    Object plrConnection = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
    plrConnection.getClass().getMethod("sendPacket", new Class[] { NMS.getNmsClass("Packet") }).invoke(plrConnection, new Object[] { packet });
  }
}