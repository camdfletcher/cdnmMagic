package me.cdnmflip.cdnmmagic.util;

import me.cdnmflip.cdnmmagic.Magic;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public final class ChatUtil {

  private ChatUtil() {
  }

  /**
   * Utility method for quickly messaging a {@link Player} with the tag automatically appended and
   * the message auto colored
   *
   * @param player The {@link Player} you would like to send a message to
   * @param message The message you would like to send
   */
  public static void msg(Player player, String message) {
    player.sendMessage(Magic.TAG + colorize(message));
  }

  /**
   * Utility method for quickly sending an error message to a {@link Player} with the tag
   * automatically appended and the message auto colored
   *
   * @param player The {@link Player} you would like to send an error message to
   * @param errorMessage The error message you would like to send
   */
  public static void error(Player player, String errorMessage) {
    msg(player, colorize("&c" + errorMessage));
  }

  /**
   * Colorizes a non-color translated string
   *
   * Effectively replaces all instances of '&' with '§'
   *
   * @param nonColoredStr The string that contains all color codes in terms of '&'
   * @return The colored string interpretable by the server
   */
  public static String colorize(String nonColoredStr) {
    return ChatColor.translateAlternateColorCodes('&', nonColoredStr);
  }

}
