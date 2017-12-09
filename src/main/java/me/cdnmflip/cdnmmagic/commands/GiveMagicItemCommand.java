package me.cdnmflip.cdnmmagic.commands;

import me.cdnmflip.cdnmmagic.Magic;
import me.cdnmflip.cdnmmagic.data.MagicItem;
import me.cdnmflip.cdnmmagic.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * @author codenameflip
 * @since 12/8/17
 */
public class GiveMagicItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("cdnmmagic.giveMagicItem"))
        {
            ChatUtil.error(player, "You do not have permission to execute this command!");
            return true;
        }

        if (args.length < 3)
        {
            ChatUtil.error(player, "Usage: /giveMagicItem (player) (item id) (amount)");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        Optional<MagicItem> item = Magic.get().getRegistry().getItem(args[1]);
        int amount = -1;

        // Target player validation
        if (target == null)
        {
            ChatUtil.error(player, "Invalid player target! Are you sure they're online?");
            return true;
        }

        // Item parameter validation
        if (!item.isPresent())
        {
            ChatUtil.error(player, "Invalid item identifier! &7&oValid items are...");

            Magic.get().getRegistry().getItems().forEach(magicItem ->
                    ChatUtil.msg(player, " &7- &f" + magicItem.getIdentifier()));

            return true;
        }

        // Amount parameter validation
        try
        {
            amount = Integer.parseInt(args[2]);

            if (amount > 64)
            {
                ChatUtil.error(player, "Too many items specified! &7(You cannot give more than 64 items at once)");
                return true;
            }
        }
        catch (NumberFormatException e)
        {
            ChatUtil.error(player, "Invalid amount specified! Make sure it's a valid number. (e.g. 1, 2, 10, 64)");
            return true;
        }

        ChatUtil.msg(player, "&aYou have been given &ex" + amount + " " + item.get().getDisplayName() + "&a!");

        if (player.getInventory().firstEmpty() == -1)
        {
            // No slot open for deposit...
            // Drop on the floor beneath the player

            player.getWorld().dropItem(player.getLocation(), item.get().generateItemStack(player, amount));
            ChatUtil.msg(player, " &7&o(There was not enough space in your inventory so the items were placed on the ground!)");
        }
        else
        {
            player.getInventory().addItem(item.get().generateItemStack(player, amount));
        }

        return true;
    }

}
