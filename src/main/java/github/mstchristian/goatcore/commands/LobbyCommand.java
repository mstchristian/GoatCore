//Copyright (c) 2025 mst.christian_
//Licensed under the MIT License. See LICENSE file in the project root for full license information.

package github.mstchristian.goatcore.commands;

import github.mstchristian.goatcore.GoatCore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand implements CommandExecutor {
    private final GoatCore plugin;

    public LobbyCommand(GoatCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("goatcore.lobby")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
            return true;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players");
            return true;
        }

        var config = plugin.getConfig();
        Location lobbyLocation = config.getLocation("lobby.location");

        if (lobbyLocation == null) {
            player.sendMessage(ChatColor.RED + "Lobby location is not set");
            return true;
        }

        player.teleport(lobbyLocation);
        player.sendMessage(ChatColor.GREEN + "Teleported to the lobby");
        return true;
    }
}
