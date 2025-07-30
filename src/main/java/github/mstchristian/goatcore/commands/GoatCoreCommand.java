//Copyright (c) 2025 mst.christian_
//Licensed under the MIT License. See LICENSE file in the project root for full license information.

package github.mstchristian.goatcore.commands;

import github.mstchristian.goatcore.GoatCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class GoatCoreCommand implements CommandExecutor {
    private final GoatCore plugin;

    public GoatCoreCommand(GoatCore plugin) {
        this.plugin = plugin;
    }

    private static final String LINE_SEPARATOR = ChatColor.GRAY + "________________";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("goatcore.command")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
            return true;
        }

        sendPluginInfo(sender);
        return true;
    }

    private void sendPluginInfo(CommandSender sender) {
        var desc = plugin.getDescription();
        String author = "Unknown";
        List<String> authors = desc.getAuthors();
        if (authors != null && !authors.isEmpty()) {
            author = authors.get(0);
        }

        String website = desc.getWebsite();
        if (website == null || website.isEmpty()) {
            website = "N/A";
        }

        sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + desc.getName());
        sender.sendMessage(LINE_SEPARATOR);

        sender.sendMessage(ChatColor.AQUA + "Name: " + ChatColor.WHITE + desc.getName());
        sender.sendMessage(ChatColor.AQUA + "Version: " + ChatColor.WHITE + desc.getVersion());
        sender.sendMessage(ChatColor.AQUA + "License: " + ChatColor.WHITE + "MIT");
        sender.sendMessage(ChatColor.AQUA + "Author: " + ChatColor.WHITE + author);
        sender.sendMessage(ChatColor.AQUA + "Description: " + ChatColor.WHITE + desc.getDescription());
        sender.sendMessage(ChatColor.AQUA + "Website: " + ChatColor.WHITE + website);

        sender.sendMessage(LINE_SEPARATOR);
    }
}
