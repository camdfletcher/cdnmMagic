package me.cdnmflip.cdnmmagic.data;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public abstract class MagicItem {

  public Map<UUID, Long> COOLDOWN_EXPIRATIONS = new HashMap<>();

  @Getter private final MagicItemType type;
  @Getter private final String identifier;
  @Getter private final String displayName;
  @Getter private final long cooldownTime;
  @Getter private final String[] description;

  public MagicItem(MagicItemType type, String identifier, String displayName, long cooldown,
      String... description) {
    this.type = type;
    this.identifier = identifier;
    this.displayName = displayName;
    this.cooldownTime = cooldown;
    this.description = description;
  }

  /**
   * Gets the time currently stored until a certain {@link UUID}'s cooldown expires
   *
   * @param uuid The {@link UUID} that is currently on cooldown
   * @return The time (in milliseconds) at which the {@link UUID}'s cooldown will expire
   */
  public long getCooldownExpirationTime(UUID uuid) {
    Objects.requireNonNull(uuid, "uuid");

    return COOLDOWN_EXPIRATIONS.getOrDefault(uuid, System.currentTimeMillis());
  }

  /**
   * Gets the time currently stored until a certain {@link UUID} can use an item once again
   *
   * @param uuid The {@link UUID} that is currently on cooldown
   * @return The time (in milliseconds) until the item will be usable again by the uuid
   */
  public long getTimeUntilCooldownExpiration(UUID uuid) {
    Objects.requireNonNull(uuid, "uuid");

    return getCooldownExpirationTime(uuid) - System.currentTimeMillis();
  }

  /**
   * Generates an {@link ItemStack} instance using a {@link Player} object for any information, if
   * necessary
   *
   * @param player The {@link Player} who the item should be tailored to (if applicable)
   * @param amount The desired quantity of items that should be generated (cannot be greater than
   * 64)
   * @return The generated {@link ItemStack}
   */
  public abstract ItemStack generateItemStack(Player player, int amount);

  /**
   * Validates that a {@link Player} is eligible to use a certain magic item
   *
   * @param player The {@link Player} you are confirming can use the item
   * @return true/false whether the player can use the item
   */
  public abstract boolean canCast(Player player);

  /**
   * The action that is performed when an item is consumed
   *
   * @param player The {@link Player} who used the item
   */
  public abstract void onCast(Player player);

  /**
   * Wrapper method that safely performs an item's action
   *
   * @param caster The {@link Player} who will be using the item
   */
  public void cast(Player caster) {
    Objects.requireNonNull(caster, "caster");

    if (canCast(caster)) {
      onCast(caster);
      handleCooldown(caster);
    }
  }

  /**
   * Automatically wraps the processing of cooldown management (for readability)
   *
   * @param player The {@link Player} that is being put on cooldown
   */
  protected void handleCooldown(Player player) {
    Objects.requireNonNull(player, "player");

    COOLDOWN_EXPIRATIONS.put(player.getUniqueId(), System.currentTimeMillis() + getCooldownTime());
  }

}
