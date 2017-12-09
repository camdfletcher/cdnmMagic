package me.cdnmflip.cdnmmagic.items.spells;

import me.cdnmflip.cdnmmagic.items.SpellItem;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.TimeUnit;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public class ShadowWalkerSpell extends SpellItem {

    public ShadowWalkerSpell()
    {
        super("shadowwalker",
                "§3§lShadow Walker Spell",
                TimeUnit.MINUTES.toMillis(1),
                DyeColor.CYAN,
                "&fFor a period of &68 seconds &fyou",
                "&fwill become &ecompletely invisible &fand",
                "&fwill disappear in a cloud of particles!");
    }

    @Override
    public void onConsumption(Player player, ItemStack consumed)
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

    @Override
    public ItemStack generateItemStack(Player player, int amount)
    {
        return generateDefaultItem(player, amount);
    }

    @Override
    public boolean canCast(Player player)
    {
        return false;
    }

    @Override
    public void onCast(Player player)
    {

    }

}
