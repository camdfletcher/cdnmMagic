package me.cdnmflip.cdnmmagic.data;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public abstract class MagicItem {

    public Map<UUID, Long> COOLDOWN_EXPIRATIONS = new HashMap<>();

    private MagicItemType type;
    private String identifier;
    private long cooldown;

    public MagicItem(MagicItemType type, String identifier, long cooldown)
    {
        this.type = type;
        this.identifier = identifier;
        this.cooldown = cooldown;
    }

    /**
     * Gets the {@link MagicItemType} enum value of the object
     *
     * @return {@link MagicItemType}
     */
    public MagicItemType getType()
    {
        return type;
    }

    /**
     * The string identifier used to compare and index items
     *
     * @return The "name" or "id" of the item being created
     */
    public String getIdentifier()
    {
        return identifier;
    }

    /**
     * Gets the cooldown of the item when activated
     *
     * @return The duration in milliseconds
     */
    public long getCooldownTime()
    {
        return cooldown;
    }

    /**
     * Gets the time currently stored until a certain {@link UUID}'s cooldown expires
     *
     * @param uuid The {@link UUID} that is currently on cooldown
     * @return The time (in milliseconds) at which the {@link UUID}'s cooldown will expire
     */
    public long getCooldownExpirationTime(UUID uuid)
    {
        Objects.requireNonNull(uuid, "uuid");

        return COOLDOWN_EXPIRATIONS.getOrDefault(uuid, System.currentTimeMillis());
    }

    /**
     * Gets the time currently stored until a certain {@link UUID} can use an item once again
     *
     * @param uuid The {@link UUID} that is currently on cooldown
     * @return The time (in milliseconds) until the item will be usable again by the uuid
     */
    public long getTimeUntilCooldownExpiration(UUID uuid)
    {
        Objects.requireNonNull(uuid, "uuid");

        return getCooldownExpirationTime(uuid) - System.currentTimeMillis();
    }

    /**
     * Generates an {@link ItemStack} instance using a {@link Player} object for any information, if necessary
     *
     * @param player The {@link Player} who the item should be tailored to (if applicable)
     * @return
     */
    public abstract ItemStack generateItemStack(Player player);

    /**
     * Validates that a {@link Player} is eligible to use a certain magic item
     *
     * @param player The {@link Player} you are confirming can use the item
     * @return true/false whether the player can use the item
     */
    public abstract boolean canCast(Player player);

    /**
     * The action that is performed when an item is consumed
     *
     * @param player The {@link Player} who used the item
     */
    public abstract void onCast(Player player);

    /**
     * Wrapper method that safely performs an item's action
     *
     * @param caster The {@link Player} who will be using the item
     */
    public void cast(Player caster)
    {
        Objects.requireNonNull(caster, "caster");

        if (canCast(caster))
        {
            onCast(caster);
        }
    }

}
