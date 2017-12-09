package me.cdnmflip.cdnmmagic.items;

import me.cdnmflip.cdnmmagic.data.ConsumableMagicItem;
import me.cdnmflip.cdnmmagic.data.MagicItemType;
import me.cdnmflip.cdnmmagic.util.ChatUtil;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public abstract class SpellItem extends ConsumableMagicItem {

    private DyeColor color;

    public SpellItem(String identifier, String displayName, long cooldown, DyeColor color, String... description)
    {
        super(MagicItemType.ITEMSTACK, identifier, displayName, cooldown, description);
        this.color = color;
    }

    public ItemStack generateDefaultItem(Player player, int amount, String... additional)
    {
        ItemStack itemStack = new ItemStack(Material.INK_SACK);
        itemStack.setDurability(color.getWoolData());
        itemStack.setAmount(amount);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.colorize(getDisplayName() + " &7&l(Right Click)"));

        // Lore generation
        List<String> lore = new ArrayList<>();
        lore.add(ChatUtil.colorize("&7&oConsumable Spell"));
        lore.add(" ");

        for (String descriptionLine : getDescription())
        {
            lore.add(ChatUtil.colorize(descriptionLine));
        }

        for (String additionalLine : additional)
        {
            lore.add(ChatUtil.colorize(additionalLine));
        }

        lore.add(" ");
        lore.add(ChatUtil.colorize("&c&lConsumed upon use!"));

        itemStack.setItemMeta(itemMeta);

        // TODO: If NBT tags are implemented, this will be where they have to be assigned.

        return itemStack;
    }

}
