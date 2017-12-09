package me.cdnmflip.cdnmmagic.registry;

import me.cdnmflip.cdnmmagic.data.IMagicRegistry;
import me.cdnmflip.cdnmmagic.data.MagicItem;
import me.cdnmflip.cdnmmagic.items.spells.JuggernautSpell;
import me.cdnmflip.cdnmmagic.items.spells.ShadowWalkerSpell;
import me.cdnmflip.cdnmmagic.util.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        MAGIC_ITEMS.addAll(Stream.of(
                new ShadowWalkerSpell(),
                new JuggernautSpell()
        ).collect(Collectors.toList()));
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
    public Optional<MagicItem> getItem(ItemStack itemStack)
    {
        NBTItem nbtItem = new NBTItem(itemStack);

        if (!nbtItem.hasKey("magic_item"))
        {
            return Optional.empty();
        }

        String identifier = nbtItem.getString("magic_item");

        return getItem(identifier);
    }

    @Override
    public boolean isMagicItem(ItemStack itemStack)
    {
        return new NBTItem(itemStack).hasKey("magic_item");
    }

}
