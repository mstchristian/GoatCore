//Copyright (c) 2025 mst.christian_
//Licensed under the MIT License. See LICENSE file in the project root for full license information.

package github.mstchristian.goatcore.commands;

import github.mstchristian.goatcore.GoatCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GoatCoreCommand implements CommandExecutor {
    private final GoatCore plugin;

    public GoatCoreCommand(GoatCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("goatcore.command")) {
            String name = this.plugin.getDescription().getName();

            sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + name);
            sender.sendMessage(ChatColor.GRAY + "________________");
            sender.sendMessage(ChatColor.AQUA + "Name: " + ChatColor.WHITE + name);
            sender.sendMessage(ChatColor.AQUA + "Version: " + ChatColor.WHITE + this.plugin.getDescription().getVersion());
            sender.sendMessage(ChatColor.AQUA + "License: " + ChatColor.WHITE + "MIT");
            sender.sendMessage(ChatColor.AQUA + "Author: " + ChatColor.WHITE + this.plugin.getDescription().getAuthors().getFirst());
            sender.sendMessage(ChatColor.AQUA + "Description: " + ChatColor.WHITE + this.plugin.getDescription().getDescription());
            sender.sendMessage(ChatColor.AQUA + "Website: " + ChatColor.WHITE + this.plugin.getDescription().getWebsite());
            sender.sendMessage(ChatColor.GRAY + "________________");
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        }

        return true;
    }
}
