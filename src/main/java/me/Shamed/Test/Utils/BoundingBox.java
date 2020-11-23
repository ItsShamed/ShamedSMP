package me.Shamed.Test.Utils;

import net.minecraft.server.v1_16_R3.AxisAlignedBB;
import org.bukkit.util.Vector;


public class BoundingBox
{
  Vector max;
  Vector min;
  
  BoundingBox(Vector min, Vector max) {
    this.max = max;
    this.min = min;
  }



















  
  public BoundingBox(AxisAlignedBB paramAxisAlignedBB) {}


















  
  public Vector midPoint() {
    return this.max.clone().add(this.min).multiply(0.5D);
  }
}