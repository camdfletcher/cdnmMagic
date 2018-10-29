package me.cdnmflip.cdnmmagic.util.item;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * @author codenameflip
 * @since 12/9/17
 */
public final class ItemStackBuilder {

  private final ItemStack ITEM_STACK;

  public ItemStackBuilder(Material mat) {
    this.ITEM_STACK = new ItemStack(mat);
  }

  public ItemStackBuilder(ItemStack item) {
    this.ITEM_STACK = item;
  }

  /**
   * Sets the item's {@link Material} enum value
   *
   * @param material The target material you'd like to use
   * @return {@link ItemStackBuilder}
   */
  public ItemStackBuilder withType(Material material) {
    this.ITEM_STACK.setType(material);

    return this;
  }

  /**
   * Set the amount of an {@link ItemStack} you'd like to have built
   * The upper-bound is 64 per stack
   *
   * @param amount The desired amount of a certain item you'd like to create
   * @return {@link ItemStackBuilder}
   */
  public ItemStackBuilder withAmount(int amount) {
    this.ITEM_STACK.setAmount(amount > 64 ? 64 : amount);
    return this;
  }

  /**
   * Sets the item's durability value
   *
   * @param durability The durability amount you wish to construct the item with
   * @return {@link ItemStackBuilder}
   */
  public ItemStackBuilder withDurability(int durability) {
    this.ITEM_STACK.setDurability((short) durability);

    return this;
  }

  /**
   * Sets the item's data value
   *
   * @param data The data value you wish to construct the item with
   * @return {@link ItemStackBuilder}
   */
  public ItemStackBuilder withData(int data) {
    this.ITEM_STACK.setDurability((short) data);

    return this;
  }

  /**
   * Set the name for the item
   * Updates the {@link ItemMeta} display name
   *
   * @param name The string you'd like to use for the item's name (colors accepted)
   * @return {@link ItemStackBuilder}
   */
  public ItemStackBuilder withName(String name) {
    ItemMeta meta = this.ITEM_STACK.getItemMeta();
    meta.setDisplayName(this.color(name));

    this.ITEM_STACK.setItemMeta(meta);
    return this;
  }

  /**
   * Appends a string to the lore of an item
   * This can be ran multiple times, each time will add
   * one string to the existing list. No list required
   *
   * @param name The lore line you'd like to add (colors accepted)
   * @return {@link ItemStackBuilder}
   */
  public ItemStackBuilder withLore(String name) {
    ItemMeta meta = this.ITEM_STACK.getItemMeta();
    List<String> lore = meta.getLore();

    if (lore == null) {
      lore = new ArrayList<>();
    }

    lore.add(this.color(name));
    meta.setLore(lore);

    this.ITEM_STACK.setItemMeta(meta);
    return this;
  }

  /**
   * Adds an enchantment to the item
   *
   * @param enchantment The {@link Enchantment} you're adding to the item
   * @param level The level of the enchantment you're adding
   * @return {@link ItemStackBuilder}
   */
  public ItemStackBuilder withEnchantment(Enchantment enchantment, int level) {
    this.ITEM_STACK.addUnsafeEnchantment(enchantment, level);

    return this;
  }

  /**
   * @see #withEnchantment(Enchantment, int)
   */
  public ItemStackBuilder withEnchantment(Enchantment enchantment) {
    this.ITEM_STACK.addUnsafeEnchantment(enchantment, 1);

    return this;
  }

  /**
   * Appends an {@link ItemFlag} to the contructed item
   *
   * @param flag The flag you would like added to the item
   * @return {@link ItemStackBuilder}
   */
  public ItemStackBuilder withFlag(ItemFlag flag) {
    ItemMeta meta = this.ITEM_STACK.getItemMeta();
    meta.addItemFlags(flag);

    this.ITEM_STACK.setItemMeta(meta);
    return this;
  }

  /**
   * Sets the item's color, if the {@link Material} is a leather armor-piece.
   *
   * @param color The desired {@link Color}
   * @return {@link ItemStackBuilder}
   */
  public ItemStackBuilder withColor(Color color) {
    Material type = this.ITEM_STACK.getType();

    if (type != Material.LEATHER_BOOTS && type != Material.LEATHER_CHESTPLATE
        && type != Material.LEATHER_HELMET && type != Material.LEATHER_LEGGINGS) {
      throw new IllegalArgumentException("withColor is only applicable for leather armor!");
    } else {
      LeatherArmorMeta meta = (LeatherArmorMeta) this.ITEM_STACK.getItemMeta();
      meta.setColor(color);

      this.ITEM_STACK.setItemMeta(meta);
      return this;
    }
  }

  /**
   * Wipes all strings from the item's lore
   *
   * @return {@link ItemStackBuilder}
   */
  public ItemStackBuilder clearLore() {
    ItemMeta meta = this.ITEM_STACK.getItemMeta();
    meta.setLore(new ArrayList<>());

    this.ITEM_STACK.setItemMeta(meta);
    return this;
  }

  /**
   * Wipes all enchantments from the item's lore
   *
   * @return {@link ItemStackBuilder}
   */
  public ItemStackBuilder clearEnchantments() {
    for (Enchantment enchantment : this.ITEM_STACK.getEnchantments().keySet()) {
      this.ITEM_STACK.removeEnchantment(enchantment);
    }

    return this;
  }

  /**
   * Gives your item an enchantment glow, but without the enchantment showing
   *
   * @return {@link ItemStackBuilder}
   */
  public ItemStackBuilder withGlow() {
    ItemMeta meta = this.ITEM_STACK.getItemMeta();
    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

    this.ITEM_STACK.setItemMeta(meta);
    this.ITEM_STACK.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

    return this;
  }

  /**
   * Constructs an {@link ItemStack} instance with the given specification from the builder
   *
   * @return The final {@link ItemStack} object
   */
  public ItemStack build() {
    return this.ITEM_STACK;
  }

  /**
   * A utility method to translate any foreign color-codes into
   * server-interpretable codes (& -> §)
   *
   * @param string The raw string that needs to be translated
   * @return The newly-formatted string
   */
  private String color(String string) {
    return ChatColor.translateAlternateColorCodes('&', string);
  }

}
