package me.cdnmflip.cdnmmagic.items.spells;

import me.cdnmflip.cdnmmagic.items.SpellItem;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.TimeUnit;

/**
 * @author codenameflip
 * @since 12/9/17
 */
public class JuggernautSpell extends SpellItem {

    public JuggernautSpell()
    {
        super("juggernaut",
                "&6&lJuggernaut Spell",
                TimeUnit.MINUTES.toMillis(2),
                DyeColor.ORANGE,
                "&fInstantly gain an &c&lEPIC &eStrength III",
                "&fboost when consumed! But beware! You will also",
                "&freceive a &2&lDEBUFF &fwhile the Strength is active!"
        );
    }

    @Override
    public boolean canCast(Player player)
    {
        return true;
    }

    @Override
    public void onCast(Player player)
    {
        // Strength
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 7, 2, false, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20 * 7, 1, false, true));

        player.getWorld().strikeLightningEffect(player.getLocation());
    }

}
