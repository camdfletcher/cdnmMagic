package me.cdnmflip.cdnmmagic.items.spells;

import me.cdnmflip.cdnmmagic.items.SpellItem;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
        return true; // Can always be activated by default
    }

    @Override
    public void onCast(Player player)
    {
        // TODO: Cooldown stuff.

        // Invis
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 8, 0, false, true));

        // Particles
        player.getWorld().playEffect(player.getLocation(), Effect.EXPLOSION_HUGE, 0);
    }

}
