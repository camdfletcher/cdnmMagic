package me.cdnmflip.cdnmmagic.listeners;

import me.cdnmflip.cdnmmagic.Magic;
import me.cdnmflip.cdnmmagic.data.ConsumableMagicItem;
import me.cdnmflip.cdnmmagic.data.MagicItem;
import me.cdnmflip.cdnmmagic.data.MagicItemType;
import me.cdnmflip.cdnmmagic.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
                    long timeUntilExpiration = TimeUnit.MILLISECONDS.toSeconds(magicItem.get().getTimeUntilCooldownExpiration(player.getUniqueId()));

                    ChatUtil.error(player, "You're currently on cooldown for the " + magicItem.get().getDisplayName() + "&c!");
                    ChatUtil.error(player, " &7&o(Your expiration will end in &6" + timeUntilExpiration + " seconds&7&o)");

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
