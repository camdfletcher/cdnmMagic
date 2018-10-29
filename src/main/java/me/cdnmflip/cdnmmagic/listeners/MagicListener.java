package me.cdnmflip.cdnmmagic.listeners;

import me.cdnmflip.cdnmmagic.data.MagicItem;
import me.cdnmflip.cdnmmagic.util.ChatUtil;
import me.cdnmflip.cdnmmagic.util.TimeUtil;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Optional;

/**
 * @author codenameflip
 * @since 12/9/17
 */
public interface MagicListener extends Listener {

  /**
   * Helper method that validates whether a {@link MagicItem} is valid in the context of a {@link
   * Listener}
   *
   * @param magicItem The {@link MagicItem} that is being validated
   * @param player The {@link Player} who is in possession of the {@link
   * org.bukkit.inventory.ItemStack} that is being checked
   * @return true = the item is valid / false = the item is not valid
   */
  default boolean validateMagicItem(Optional<MagicItem> magicItem, Player player) {
    if (!magicItem.isPresent()) {
      ChatUtil.error(player, "Unable to reference item id from this magic item!");
      ChatUtil.error(player, "Report this issue to an administrator for assistance.");

      return false;
    }

    long cooldownExpirationTime = magicItem.get().getCooldownExpirationTime(player.getUniqueId());

    if (cooldownExpirationTime <= System.currentTimeMillis()) {
      magicItem.get().COOLDOWN_EXPIRATIONS.remove(player.getUniqueId()); // Cooldown expriation over
    } else if (cooldownExpirationTime > System.currentTimeMillis()) {
      long timeUntilExpiration = magicItem.get()
          .getTimeUntilCooldownExpiration(player.getUniqueId());

      ChatUtil.error(player, "Slow down! You're on cooldown for another §6" + TimeUtil
          .getDurationBreakdown(timeUntilExpiration));
      player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);

      return false;
    }

    return true;
  }

}
