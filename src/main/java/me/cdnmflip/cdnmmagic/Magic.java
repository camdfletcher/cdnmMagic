package me.cdnmflip.cdnmmagic;

import me.cdnmflip.cdnmmagic.commands.GiveMagicItemCommand;
import me.cdnmflip.cdnmmagic.data.IMagicRegistry;
import me.cdnmflip.cdnmmagic.listeners.ItemListener;
import me.cdnmflip.cdnmmagic.registry.SimpleMagicRegistry;
import me.cdnmflip.cdnmmagic.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class Magic extends JavaPlugin {

    public static final String TAG = ChatUtil.colorize("&d&l[Magic] &f");

    private static Magic instance;
    private IMagicRegistry registry;

    @Override
    public void onEnable()
    {
        instance = this;
        registry = new SimpleMagicRegistry(); // Default to the simple registry. Other plugins are free to override
        registry.loadMagic();

        registerListeners(
                new ItemListener()
        );

        getCommand("giveMagicItem").setExecutor(new GiveMagicItemCommand());
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

    private void registerListeners(Listener... listeners)
    {
        PluginManager pm = Bukkit.getPluginManager();

        Arrays.stream(listeners).forEach(listener -> pm.registerEvents(listener, this));
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
