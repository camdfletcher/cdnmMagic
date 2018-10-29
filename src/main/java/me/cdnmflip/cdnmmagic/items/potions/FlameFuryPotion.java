package me.cdnmflip.cdnmmagic.items.potions;

import me.cdnmflip.cdnmmagic.items.PotionItem;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.concurrent.TimeUnit;

/**
 * @author codenameflip
 * @since 12/9/17
 */
public class FlameFuryPotion extends PotionItem {

  public FlameFuryPotion() {
    super("flamefury",
        "&c&lFlame Fury Potion",
        TimeUnit.MINUTES.toMillis(2),
        PotionType.FIRE_RESISTANCE,
        "&fOn consumption gain a resistance to",
        "&fall flame based attacks and instantly",
        "&fset enemies around you on fire!"
    );
  }

  @Override
  public boolean canCast(Player player) {
    return true;
  }

  @Override
  public void onCast(Player player) {
    // Fire Resistance
    player.addPotionEffect(
        new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 20, 0, true, true));

    player.getNearbyEntities(10, 10, 10).forEach(entity ->
    {
      if (entity instanceof Monster) {
        entity.setFireTicks(20 * 10);
        entity.getWorld().strikeLightningEffect(entity.getLocation());
      }
    });
  }

}
