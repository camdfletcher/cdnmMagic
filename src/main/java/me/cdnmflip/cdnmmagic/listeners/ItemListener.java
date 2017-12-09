package me.cdnmflip.cdnmmagic.listeners;

import me.cdnmflip.cdnmmagic.Magic;
import me.cdnmflip.cdnmmagic.data.ConsumableMagicItem;
import me.cdnmflip.cdnmmagic.data.MagicItem;
import me.cdnmflip.cdnmmagic.data.MagicItemType;
import me.cdnmflip.cdnmmagic.util.ChatUtil;
import me.cdnmflip.cdnmmagic.util.TimeUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public class ItemListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack hand = event.getItem();

        if (hand != null && hand.getType() != Material.AIR)
        {
            if (Magic.get().getRegistry().isMagicItem(hand))
            {
                Optional<MagicItem> magicItem = Magic.get().getRegistry().getItem(hand);

                if (!magicItem.isPresent())
                {
                    ChatUtil.error(player, "Unable to reference item id from this magic item!");
                    ChatUtil.error(player, "Report this issue to an administrator for assistance.");

                    return;
                }

                long cooldownExpirationTime = magicItem.get().getCooldownExpirationTime(player.getUniqueId());

                if (cooldownExpirationTime <= System.currentTimeMillis())
                {
                    magicItem.get().COOLDOWN_EXPIRATIONS.remove(player.getUniqueId()); // Cooldown expriation over
                }
                else if (cooldownExpirationTime > System.currentTimeMillis())
                {
                    long timeUntilExpiration = magicItem.get().getTimeUntilCooldownExpiration(player.getUniqueId());

                    ChatUtil.error(player, "Slow down! You're on cooldown for another §6" + TimeUtil.getDurationBreakdown(timeUntilExpiration));

                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);

                    return;
                }

                if (magicItem.get().getType() == MagicItemType.ITEMSTACK)
                {
                    ConsumableMagicItem consumableMagicItem = (ConsumableMagicItem) magicItem.get();

                    // Handle deduction of the item and cast the spell
                    consumableMagicItem.onConsumption(player, hand);
                    consumableMagicItem.cast(player);
                }
                else
                {
                    ChatUtil.error(player, "Unsupported magic item type! Contact an administrator for assistance.");
                }
            }
        }
    }

}
