package me.Shamed.Test.Utils.PacketWrapper;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

















public class WrapperPlayClientSteerVehicle
  extends AbstractPacket
{
  public static final PacketType TYPE = PacketType.Play.Client.STEER_VEHICLE;
  
  public WrapperPlayClientSteerVehicle() {
    super(new PacketContainer(TYPE), TYPE);
    this.handle.getModifier().writeDefaults();
  }
  
  public WrapperPlayClientSteerVehicle(PacketContainer packet) {
    super(packet, TYPE);
  }







  
  public float getSideways() {
    return ((Float)this.handle.getFloat().read(0)).floatValue();
  }





  
  public void setSideways(float value) {
    this.handle.getFloat().write(0, Float.valueOf(value));
  }







  
  public float getForward() {
    return ((Float)this.handle.getFloat().read(1)).floatValue();
  }





  
  public void setForward(float value) {
    this.handle.getFloat().write(1, Float.valueOf(value));
  }
  
  public boolean isJump() {
    return ((Boolean)this.handle.getBooleans().read(0)).booleanValue();
  }
  
  public void setJump(boolean value) {
    this.handle.getBooleans().write(0, Boolean.valueOf(value));
  }
  
  public boolean isUnmount() {
    return ((Boolean)this.handle.getBooleans().read(1)).booleanValue();
  }
  
  public void setUnmount(boolean value) {
    this.handle.getBooleans().write(1, Boolean.valueOf(value));
  }
}