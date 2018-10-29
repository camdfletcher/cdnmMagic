package me.cdnmflip.cdnmmagic.items;

import me.cdnmflip.cdnmmagic.data.ConsumableMagicItem;
import me.cdnmflip.cdnmmagic.data.MagicItemType;
import me.cdnmflip.cdnmmagic.util.item.ItemStackBuilder;
import me.cdnmflip.cdnmmagic.util.item.NBTItem;
import me.cdnmflip.cdnmmagic.util.TimeUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

import java.util.Arrays;

/**
 * @author codenameflip
 * @since 12/9/17
 */
public abstract class PotionItem extends ConsumableMagicItem {

  private final PotionType potionType;

  public PotionItem(String identifier, String displayName, long cooldown, PotionType potionType,
      String... description) {
    super(MagicItemType.POTION, identifier, displayName, cooldown, description);
    this.potionType = potionType;
  }

  @Override
  public void onConsumption(Player player, ItemStack consumed) {
    if (consumed.getAmount() > 1) {
      ItemStack newStack = consumed.clone();
      newStack.setAmount(consumed.getAmount() - 1);

      player.setItemInHand(newStack);
    } else {
      player.setItemInHand(new ItemStack(
          Material.AIR)); // Messy work around for the removal of ALL unstacked potions
    }
  }

  @Override
  public ItemStack generateItemStack(Player player, int amount) {
    return generateDefaultItem(player, amount);
  }

  /**
   * Automatically generates a magic potion item using the specified template format
   *
   * @param player The {@link Player} who will be used to customize the item (if applicable)
   * @param amount The number of spells that should be generated in this stack (NOTE: Cannot be >
   * 64)
   * @param additional Any additional text that should be appended under the description in the
   * lore
   * @return A generated {@link ItemStack}
   */
  private ItemStack generateDefaultItem(Player player, int amount, String... additional) {
    ItemStackBuilder builder = new ItemStackBuilder(Material.POTION)
        .withAmount(amount)
        .withData(potionType.getDamageValue())
        .withGlow()
        .withFlag(ItemFlag.HIDE_POTION_EFFECTS)
        .withName(getDisplayName() + " &7&l(Drink)")
        .withLore("&7&oConsumable Magic Potion")
        .withLore(" ");

    Arrays.stream(getDescription()).forEach(builder::withLore);

    builder.withLore(" ");

    if (additional.length > 0) {
      Arrays.stream(additional).forEach(builder::withLore);
      builder.withLore(" ");
    }

    builder.withLore("&fCooldown &6" + TimeUtil.getDurationBreakdown(getCooldownTime()));

    NBTItem nbtItem = new NBTItem(builder.build());
    nbtItem.setString("magic_item", this.getIdentifier());

    return nbtItem.getItem();
  }

}
