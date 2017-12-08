package me.cdnmflip.cdnmmagic.data;

import org.bukkit.inventory.ItemStack;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public abstract class ConsumableMagicItem extends MagicItem {

    public ConsumableMagicItem(MagicItemType type, String identifier, long cooldown)
    {
        super(type, identifier, cooldown);
    }

    /**
     * Determines how the {@link ItemStack} should be handled when consumed/activated
     * e.g. If the item is an ITEMSTACK item type then remove one of the items from the potential stack
     *
     * @param consumed The {@link ItemStack} that was click and needs to be "consumed"
     */
    public abstract void onConsumption(ItemStack consumed);

}
