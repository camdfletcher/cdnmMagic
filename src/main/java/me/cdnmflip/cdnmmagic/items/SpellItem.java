package me.cdnmflip.cdnmmagic.items;

import me.cdnmflip.cdnmmagic.data.ConsumableMagicItem;
import me.cdnmflip.cdnmmagic.data.MagicItemType;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public abstract class SpellItem extends ConsumableMagicItem {

    public SpellItem(String identifier, long cooldown)
    {
        super(MagicItemType.ITEMSTACK, identifier, cooldown);
    }

}
