package me.cdnmflip.cdnmmagic.listeners;

import me.cdnmflip.cdnmmagic.Magic;
import me.cdnmflip.cdnmmagic.data.ConsumableMagicItem;
import me.cdnmflip.cdnmmagic.data.MagicItem;
import me.cdnmflip.cdnmmagic.data.MagicItemType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public class ItemListener implements MagicListener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack hand = event.getItem();

        if (hand != null && hand.getType() != Material.AIR && hand.getType() != Material.POTION)
        {
            if (Magic.get().getRegistry().isMagicItem(hand))
            {
                Optional<MagicItem> magicItem = Magic.get().getRegistry().getItem(hand);

                if (validateMagicItem(magicItem, player))
                {
                    if (magicItem.get().getType() == MagicItemType.ITEMSTACK)
                    {
                        ConsumableMagicItem consumableMagicItem = (ConsumableMagicItem) magicItem.get();

                        // Handle deduction of the item and cast the spell
                        consumableMagicItem.onConsumption(player, hand);
                        consumableMagicItem.cast(player);
                    }
                }
            }
        }
    }

}
