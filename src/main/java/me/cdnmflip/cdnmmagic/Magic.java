package me.cdnmflip.cdnmmagic;

import org.bukkit.plugin.java.JavaPlugin;

public final class Magic extends JavaPlugin {

    private static Magic instance;

    @Override
    public void onEnable()
    {
        instance = this;
    }

    @Override
    public void onDisable()
    {
    }

    public static Magic get()
    {
        return instance;
    }

}
