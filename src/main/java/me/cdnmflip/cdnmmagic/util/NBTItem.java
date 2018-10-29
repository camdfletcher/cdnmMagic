package me.cdnmflip.cdnmmagic.util;

import org.bukkit.inventory.ItemStack;

/**
 * NOTE: This is not my class, it is an API that I have used forever
 */
public class NBTItem {

  private ItemStack itemStack;

  public NBTItem(ItemStack itemStack) {
    this.itemStack = itemStack.clone();
  }

  public ItemStack getItem() {
    return itemStack;
  }

  public Boolean hasKey(String key) {
    return NBTReflectionUtil.hasKey(itemStack, key);
  }

  public void setString(String key, String value) {
    itemStack = NBTReflectionUtil.setString(itemStack, key, value);
  }

  public String getString(String key) {
    return NBTReflectionUtil.getString(itemStack, key);
  }

  public void setInteger(String key, Integer value) {
    itemStack = NBTReflectionUtil.setInt(itemStack, key, value);
  }

  public Integer getInteger(String key) {
    return NBTReflectionUtil.getInt(itemStack, key);
  }

  public void setDouble(String key, Double value) {
    itemStack = NBTReflectionUtil.setDouble(itemStack, key, value);
  }

  public Double getDouble(String key) {
    return NBTReflectionUtil.getDouble(itemStack, key);
  }

  public void setBoolean(String key, Boolean value) {
    itemStack = NBTReflectionUtil.setBoolean(itemStack, key, value);
  }

  public Boolean getBoolean(String key) {
    return NBTReflectionUtil.getBoolean(itemStack, key);
  }

}
