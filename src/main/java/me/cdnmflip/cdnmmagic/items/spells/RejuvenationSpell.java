package me.cdnmflip.cdnmmagic.items.spells;

import me.cdnmflip.cdnmmagic.Magic;
import me.cdnmflip.cdnmmagic.items.SpellItem;
import me.cdnmflip.cdnmmagic.util.ChatUtil;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.TimeUnit;

/**
 * @author codenameflip
 * @since 12/9/17
 */
public class RejuvenationSpell extends SpellItem {

    public RejuvenationSpell()
    {
        super("rejuvenation",
                "&b&lRejuvenation Spell",
                TimeUnit.MINUTES.toMillis(4),
                DyeColor.LIGHT_BLUE,
                "&fInstantly resets &eall &fyour",
                "&fcurrent spell cooldowns."
        );
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

    @Override
    public boolean canCast(Player player)
    {
        return true;
    }

    @Override
    public void onCast(Player player)
    {
        Magic.get().getRegistry().getItems().forEach(item ->
        {
            if (item.COOLDOWN_EXPIRATIONS.containsKey(player.getUniqueId()))
            {
                item.COOLDOWN_EXPIRATIONS.remove(player.getUniqueId());

                player.sendMessage(ChatUtil.colorize("&bCooldown reset for " + item.getDisplayName() + "&b!"));
            }
        });

        // Process the cooldown AFTERWARDS so that it doesnt add the cooldown and then immediately remove it
        handleCooldown(player);
    }
}
