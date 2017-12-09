package me.cdnmflip.cdnmmagic.items;

import me.cdnmflip.cdnmmagic.data.ConsumableMagicItem;
import me.cdnmflip.cdnmmagic.data.MagicItemType;
import me.cdnmflip.cdnmmagic.util.ItemStackBuilder;
import me.cdnmflip.cdnmmagic.util.NBTItem;
import me.cdnmflip.cdnmmagic.util.TimeUtil;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

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

    @Override
    public void onConsumption(Player player, ItemStack consumed)
    {
        handleGenericConsumption(consumed, player);
    }

    @Override
    public ItemStack generateItemStack(Player player, int amount)
    {
        return generateDefaultItem(player, amount);
    }

    /**
     * Automatically generates a spell item using the specified template format
     *
     * @param player The {@link Player} who will be used to customize the item (if applicable)
     * @param amount The number of spells that should be generated in this stack (NOTE: Cannot be > 64)
     * @param additional Any additional text that should be appended under the description in the lore
     * @return A generated {@link ItemStack}
     */
    private ItemStack generateDefaultItem(Player player, int amount, String... additional)
    {
        ItemStackBuilder builder = new ItemStackBuilder(Material.INK_SACK)
                .withAmount(amount)
                .withData(color.getDyeData())
                .withGlow()
                .withName(getDisplayName() + " &7&l(Right Click)")
                .withLore("&7&oConsumable Spell")
                .withLore(" ");

        Arrays.stream(getDescription()).forEach(builder::withLore);

        builder.withLore(" ");

        if (additional.length > 0)
        {
            Arrays.stream(additional).forEach(builder::withLore);
            builder.withLore(" ");
        }

        builder.withLore("&fCooldown &6" + TimeUtil.getDurationBreakdown(getCooldownTime()))
                .withLore(" ")
                .withLore("&c&lConsumed upon use!");

        NBTItem nbtItem = new NBTItem(builder.build());
        nbtItem.setString("magic_item", this.getIdentifier());

        return nbtItem.getItem();
    }

}
