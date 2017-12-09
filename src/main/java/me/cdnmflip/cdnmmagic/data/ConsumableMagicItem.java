package me.cdnmflip.cdnmmagic.data;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public abstract class ConsumableMagicItem extends MagicItem {

    public ConsumableMagicItem(MagicItemType type, String identifier, String displayName, long cooldown, String... description)
    {
        super(type, identifier, displayName, cooldown, description);
    }

    /**
     * Determines how the {@link ItemStack} should be handled when consumed/activated
     * e.g. If the item is an ITEMSTACK item type then remove one of the items from the potential stack
     *
     * @param player The {@link Player} who consumed the {@link MagicItem}
     * @param consumed The {@link ItemStack} that was click and needs to be "consumed"
     */
    public abstract void onConsumption(Player player, ItemStack consumed);

    /**
     * Automatically handle the generic consumption of a spell
     * e.g. Remove one item from a stack or the entire stack if needed
     *
     * @param consumed The {@link ItemStack} that was consumed (clicked)
     * @param player The {@link Player} who consumed the item
     */
    protected void handleGenericConsumption(ItemStack consumed, Player player)
    {
        if (consumed.getAmount() > 1)
        {
            ItemStack newStack = consumed.clone();
            newStack.setAmount(consumed.getAmount() - 1);

            player.setItemInHand(newStack);
        }
        else
        {
            player.getInventory().remove(consumed);
        }
    }

}
