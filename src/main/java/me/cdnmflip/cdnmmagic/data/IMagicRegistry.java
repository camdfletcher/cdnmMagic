package me.cdnmflip.cdnmmagic.data;

import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.Set;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public interface IMagicRegistry {

    /**
     * Handle all loading of data that will be needed here
     * Ideally {@link MagicItem} should be loaded in here
     *
     * Designed to be flexible for any form of storage loading, e.g. SQL, YAML, hardcoded, etc...
     */
    void loadMagic();

    /**
     * Handle all cleaning up of registry data here
     *
     * Ideal for any cleanup of repeating tasks or any other passive actions that may not have been ended
     */
    void unloadMagic();

    /**
     * A centralized location for all {@link MagicItem} being maintained and indexed by the plugin
     *
     * @return {@link java.util.List} of {@link MagicItem}
     */
    Set<MagicItem> getItems();

    /**
     * Retrieves an indexed instance of a {@link MagicItem}
     *
     * @param identifier The string identifier of the desired {@link MagicItem}
     * @return An {@link Optional} containing the retrieved instance of {@link MagicItem} (if existing)
     */
    Optional<MagicItem> getItem(String identifier);

    /**
     * Checks whether or not that an {@link ItemStack} is the representation of a {@link MagicItem}
     *
     * @param itemStack The {@link ItemStack} that is being validated
     * @return true/false
     */
    boolean isMagicItem(ItemStack itemStack);

}
