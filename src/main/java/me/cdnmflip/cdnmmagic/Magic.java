package me.cdnmflip.cdnmmagic;

import java.util.stream.Stream;
import lombok.Getter;
import me.cdnmflip.cdnmmagic.commands.GiveMagicItemCommand;
import me.cdnmflip.cdnmmagic.data.IMagicRegistry;
import me.cdnmflip.cdnmmagic.listeners.ItemListener;
import me.cdnmflip.cdnmmagic.listeners.PotionListener;
import me.cdnmflip.cdnmmagic.registry.SimpleMagicRegistry;
import me.cdnmflip.cdnmmagic.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Magic extends JavaPlugin {

  public static final String TAG = ChatUtil.colorize("&d&l[Magic] &f");

  @Getter private static Magic instance;
  @Getter private IMagicRegistry registry;

  @Override
  public void onEnable() {
    instance = this;

    registry = new SimpleMagicRegistry(); // Default to the simple registry. Other plugins are free to override
    registry.loadMagic();

    Stream.of(
        new ItemListener(),
        new PotionListener()
    ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));

    getCommand("giveMagicItem").setExecutor(new GiveMagicItemCommand());
  }

  @Override
  public void onDisable() {
    registry.unloadMagic();
  }

  /**
   * Update the {@link #registry} object in this runner.
   * Should be used by other plugins to modify how the plugin
   * handles running magic-related tasks
   *
   * @param registry The new {@link IMagicRegistry} that will be utilized
   */
  public void updateRegistry(IMagicRegistry registry) {
    this.registry = registry;
    this.registry.loadMagic();
  }

}
