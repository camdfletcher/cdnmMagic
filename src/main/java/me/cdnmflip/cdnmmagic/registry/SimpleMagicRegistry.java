package me.cdnmflip.cdnmmagic.registry;

import me.cdnmflip.cdnmmagic.data.IMagicRegistry;
import me.cdnmflip.cdnmmagic.data.MagicItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public class SimpleMagicRegistry implements IMagicRegistry {

    private final Set<MagicItem> MAGIC_ITEMS = new HashSet<>();

    @Override
    public void loadMagic()
    {
        // Load all default items that will come bundled with the plugin
    }

    @Override
    public void unloadMagic()
    {
        MAGIC_ITEMS.clear();
    }

    @Override
    public Set<MagicItem> getItems()
    {
        return MAGIC_ITEMS;
    }

    @Override
    public Optional<MagicItem> getItem(String identifier)
    {
        return MAGIC_ITEMS.stream()
                .filter(item -> item.getIdentifier().equalsIgnoreCase(identifier))
                .findAny();
    }

    @Override
    public boolean isMagicItem(ItemStack itemStack)
    {
        // TODO: Not sure how comparing should be done. Ideally NBT tags are the safest/most future proof solution.
        return false;
    }

}
