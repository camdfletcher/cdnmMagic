package me.cdnmflip.cdnmmagic.items.spells;

import me.cdnmflip.cdnmmagic.items.SpellItem;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
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
                DyeColor.GRAY,
                "&fFor a period of &65 seconds &fyou",
                "&fwill become &ecompletely invisible &fand",
                "&fwill disappear in a cloud of particles!"
        );
    }

    @Override
    public boolean canCast(Player player)
    {
        return true; // Can always be activated by default
    }

    @Override
    public void onCast(Player player)
    {
        // Invis
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 5, 0, false, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 5, 1, false, true));

        // Particles
        player.getWorld().playEffect(player.getLocation(), Effect.EXPLOSION_HUGE, 0);
    }

}
