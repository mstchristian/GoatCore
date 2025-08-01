//Copyright (c) 2025 mst.christian_
//Licensed under the MIT License. See LICENSE file in the project root for full license information.

package github.mstchristian.goatcore.commands;

import github.mstchristian.goatcore.GoatCore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetLobbyCommand implements CommandExecutor, TabCompleter {
    private final GoatCore plugin;

    public SetLobbyCommand(GoatCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("goatcore.setlobby")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
            return true;
        }

        if (args.length == 0) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(ChatColor.RED + "This command can only be used by players when no arguments are provided");
                return true;
            }
            setLobbyFromPlayerLocation(player);
        } else if (args.length == 6) {
            setLobbyFromArguments(sender, args);
        } else {
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Incorrect usage.");
            sender.sendMessage(ChatColor.RED + "Use: /setlobby or /setlobby <world> <x> <y> <z> <yaw> <pitch>");
        }

        return true;
    }

    private void setLobbyFromPlayerLocation(Player player) {
        plugin.getConfig().set("lobby.location", player.getLocation());
        plugin.saveConfig();
        player.sendMessage(ChatColor.GREEN + "Lobby location set to your current position.");
    }

    private void setLobbyFromArguments(CommandSender sender, String[] args) {
        String worldName = args[0];
        World world = plugin.getServer().getWorld(worldName);

        if (world == null) {
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "World not found: " + worldName);
            return;
        }

        try {
            double x = Double.parseDouble(args[1]);
            double y = Double.parseDouble(args[2]);
            double z = Double.parseDouble(args[3]);
            float yaw = Float.parseFloat(args[4]);
            float pitch = Float.parseFloat(args[5]);

            Location location = new Location(world, x, y, z, yaw, pitch);
            plugin.getConfig().set("lobby.location", location);
            plugin.saveConfig();

            sender.sendMessage(ChatColor.GREEN + "Lobby location set to: " + ChatColor.YELLOW + world.getName() + " " + x + " " + y + " " + z + " " + yaw + " " + pitch);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Invalid coordinates.");
            sender.sendMessage(ChatColor.RED + "Make sure to use numbers for x, y, z, yaw, and pitch.");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            String partialWorld = args[0].toLowerCase();
            List<String> suggestions = new ArrayList<>();

            for (World world : plugin.getServer().getWorlds()) {
                String name = world.getName();
                if (name.toLowerCase().startsWith(partialWorld)) {
                    suggestions.add(name);
                }
            }
            return suggestions;
        }

        return List.of();
    }
}