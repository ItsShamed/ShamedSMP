package me.Shamed.Test.Utils;

import java.util.ArrayList;
import org.bukkit.Effect;
import org.bukkit.World;
import org.bukkit.util.Vector;


public class RayTrace
{
  Vector origin;
  Vector direction;
  
  public RayTrace(Vector origin, Vector direction) {
    this.origin = origin;
    this.direction = direction;
  }

  
  public Vector getPostion(double blocksAway) {
    return this.origin.clone().add(this.direction.clone().multiply(blocksAway));
  }

  
  public boolean isOnLine(Vector position) {
    double t = (position.getX() - this.origin.getX()) / this.direction.getX();
    
    if (position.getBlockY() == this.origin.getY() + t * this.direction.getY() && position.getBlockZ() == this.origin.getZ() + t * this.direction.getZ()) {
      return true;
    }
    return false;
  }

  
  public ArrayList<Vector> traverse(double blocksAway, double accuracy) {
    ArrayList<Vector> positions = new ArrayList<Vector>();
    for (double d = 0.0D; d <= blocksAway; d += accuracy) {
      positions.add(getPostion(d));
    }
    return positions;
  }

  
  public Vector positionOfIntersection(Vector min, Vector max, double blocksAway, double accuracy) {
    ArrayList<Vector> positions = traverse(blocksAway, accuracy);
    for (Vector position : positions) {
      if (intersects(position, min, max)) {
        return position;
      }
    } 
    return null;
  }

  
  public boolean intersects(Vector min, Vector max, double blocksAway, double accuracy) {
    ArrayList<Vector> positions = traverse(blocksAway, accuracy);
    for (Vector position : positions) {
      if (intersects(position, min, max)) {
        return true;
      }
    } 
    return false;
  }

  
  public Vector positionOfIntersection(BoundingBox boundingBox, double blocksAway, double accuracy) {
    ArrayList<Vector> positions = traverse(blocksAway, accuracy);
    for (Vector position : positions) {
      if (intersects(position, boundingBox.min, boundingBox.max)) {
        return position;
      }
    } 
    return null;
  }

  
  public boolean intersects(BoundingBox boundingBox, double blocksAway, double accuracy) {
    ArrayList<Vector> positions = traverse(blocksAway, accuracy);
    for (Vector position : positions) {
      if (intersects(position, boundingBox.min, boundingBox.max)) {
        return true;
      }
    } 
    return false;
  }

  
  public static boolean intersects(Vector position, Vector min, Vector max) {
    if (position.getX() < min.getX() || position.getX() > max.getX())
      return false; 
    if (position.getY() < min.getY() || position.getY() > max.getY())
      return false; 
    if (position.getZ() < min.getZ() || position.getZ() > max.getZ()) {
      return false;
    }
    return true;
  }

  
  public void highlight(World world, double blocksAway, double accuracy) {
    for (Vector position : traverse(blocksAway, accuracy))
      world.playEffect(position.toLocation(world), Effect.SMOKE, 0); 
  }
}
