package me.cdnmflip.cdnmmagic.items;

import me.cdnmflip.cdnmmagic.data.ConsumableMagicItem;
import me.cdnmflip.cdnmmagic.data.MagicItemType;
import me.cdnmflip.cdnmmagic.util.ChatUtil;
import me.cdnmflip.cdnmmagic.util.NBTItem;
import me.cdnmflip.cdnmmagic.util.TimeUtil;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
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

    /**
     * Automatically generates a spell item using the specified template format
     *
     * @param player The {@link Player} who will be used to customize the item (if applicable)
     * @param amount The number of spells that should be generated in this stack (NOTE: Cannot be > 64)
     * @param additional Any additional text that should be appended under the description in the lore
     * @return A generated {@link ItemStack}
     */
    protected ItemStack generateDefaultItem(Player player, int amount, String... additional)
    {
        ItemStack itemStack = new ItemStack(Material.INK_SACK);
        itemStack.setDurability(color.getDyeData());
        itemStack.setAmount(amount);
        itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.setDisplayName(ChatUtil.colorize(getDisplayName() + " &7&l(Right Click)"));

        // Lore generation
        List<String> lore = new ArrayList<>();
        lore.add(ChatUtil.colorize("&7&oConsumable Spell"));
        lore.add(" ");

        for (String descriptionLine : getDescription())
        {
            lore.add(ChatUtil.colorize(descriptionLine));
        }

        lore.add(" ");

        if (additional.length > 0)
        {
            for (String additionalLine : additional)
            {
                lore.add(ChatUtil.colorize(additionalLine));
            }

            lore.add(" ");
        }

        lore.add(ChatUtil.colorize("&fCooldown &6" + TimeUtil.getDurationBreakdown(getCooldownTime())));
        lore.add(" ");
        lore.add(ChatUtil.colorize("&c&lConsumed upon use!"));

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setString("magic_item", this.getIdentifier());

        return nbtItem.getItem();
    }

}
