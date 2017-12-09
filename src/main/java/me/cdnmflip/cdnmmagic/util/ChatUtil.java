package me.cdnmflip.cdnmmagic.util;

import net.md_5.bungee.api.ChatColor;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public final class ChatUtil {

    private ChatUtil()
    {
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
