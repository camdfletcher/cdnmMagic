package me.cdnmflip.cdnmmagic.listeners;

import me.cdnmflip.cdnmmagic.Magic;
import me.cdnmflip.cdnmmagic.data.ConsumableMagicItem;
import me.cdnmflip.cdnmmagic.data.MagicItem;
import me.cdnmflip.cdnmmagic.data.MagicItemType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

/**
 * @author codenameflip
 * @since 12/9/17
 */
public class PotionListener implements MagicListener {

  @EventHandler
  public void onConsumePotion(PlayerItemConsumeEvent event) {
    Player player = event.getPlayer();
    ItemStack hand = event.getItem();

    if (hand != null && hand.getType() == Material.POTION) {
      if (Magic.getInstance().getRegistry().isMagicItem(hand)) {
        Optional<MagicItem> magicItem = Magic.getInstance().getRegistry().getItem(hand);

        event.setCancelled(true);

        if (validateMagicItem(magicItem, player)) {
          if (magicItem.get().getType() == MagicItemType.POTION) {
            ConsumableMagicItem consumableMagicItem = (ConsumableMagicItem) magicItem.get();

            // Handle deduction of the potion
            consumableMagicItem.onConsumption(player, hand);
            consumableMagicItem.cast(player);
          }
        }
      }
    }
  }

}
