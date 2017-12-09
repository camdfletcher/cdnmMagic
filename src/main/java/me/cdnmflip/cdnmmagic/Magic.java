package me.cdnmflip.cdnmmagic;

import me.cdnmflip.cdnmmagic.data.IMagicRegistry;
import me.cdnmflip.cdnmmagic.registry.SimpleMagicRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public final class Magic extends JavaPlugin {

    private static Magic instance;
    private IMagicRegistry registry;

    @Override
    public void onEnable()
    {
        instance = this;
        registry = new SimpleMagicRegistry(); // Default to the simple registry. Other plugins are free to override
        registry.loadMagic();
    }

    @Override
    public void onDisable()
    {
        registry.unloadMagic();
    }

    public static Magic get()
    {
        return instance;
    }

    public IMagicRegistry getRegistry()
    {
        return registry;
    }

    public void setRegistry(IMagicRegistry registry)
    {
        this.registry = registry;
        this.registry.loadMagic();
    }

}
