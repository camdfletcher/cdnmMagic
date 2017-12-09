package me.cdnmflip.cdnmmagic.util;

import me.cdnmflip.cdnmmagic.Magic;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public final class ChatUtil {

    private ChatUtil()
    {
    }

    public static void msg(Player player, String message)
    {
        player.sendMessage(Magic.TAG + colorize(message));
    }

    public static void error(Player player, String errorMessage)
    {
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
    public static String colorize(String nonColoredStr)
    {
        return ChatColor.translateAlternateColorCodes('&', nonColoredStr);
    }

}
