//Copyright (c) 2025 mst.christian_
//Licensed under the MIT License. See LICENSE file in the project root for full license information.

package github.mstchristian.goatcore.commands;

import github.mstchristian.goatcore.GoatCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerInfoCommand implements CommandExecutor {
    private final GoatCore plugin;

    public ServerInfoCommand(GoatCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("goatcore.serverinfo")) {
            long uptimeSeconds = (System.currentTimeMillis() - plugin.getStartTime()) / 1000;
            long days = uptimeSeconds / 86400;
            uptimeSeconds %= 86400;
            long hours = uptimeSeconds / 3600;
            uptimeSeconds %= 3600;
            long minutes = uptimeSeconds / 60;
            long seconds = uptimeSeconds % 60;

            sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Server Information");
            sender.sendMessage(ChatColor.GRAY + "________________");
            sender.sendMessage(ChatColor.AQUA + "Server Name: " + ChatColor.WHITE + this.plugin.getServer().getName());
            sender.sendMessage(ChatColor.AQUA + "Server Version: " + ChatColor.WHITE + this.plugin.getServer().getVersion());
            sender.sendMessage(ChatColor.AQUA + "Bukkit Version: " + ChatColor.WHITE + this.plugin.getServer().getBukkitVersion());
            sender.sendMessage(ChatColor.AQUA + "Java Version: " + ChatColor.WHITE + System.getProperty("java.version"));
            sender.sendMessage(ChatColor.AQUA + "OS Name: " + ChatColor.WHITE + System.getProperty("os.name"));
            sender.sendMessage(ChatColor.AQUA + "Online Players: " + ChatColor.WHITE + this.plugin.getServer().getOnlinePlayers().size());
            sender.sendMessage(ChatColor.AQUA + "Max Players: " + ChatColor.WHITE + this.plugin.getServer().getMaxPlayers());
            sender.sendMessage(ChatColor.AQUA + "Server Date: " + ChatColor.WHITE + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            sender.sendMessage(ChatColor.AQUA + "Server Uptime: " + ChatColor.WHITE + days + "D " + hours + "H " + minutes + "M " + seconds + "S");
            sender.sendMessage(ChatColor.AQUA + "Server IP: " + ChatColor.WHITE + this.plugin.getServer().getIp());
            sender.sendMessage(ChatColor.AQUA + "Server Port: " + ChatColor.WHITE + this.plugin.getServer().getPort());
            sender.sendMessage(ChatColor.AQUA + "Used Memory: " + ChatColor.WHITE + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " MB");
            sender.sendMessage(ChatColor.AQUA + "Max Memory: " + ChatColor.WHITE + Runtime.getRuntime().maxMemory() / (1024 * 1024) + " MB");

            if (sender instanceof Player player) {
                player.sendMessage(ChatColor.AQUA + "Current World: " + ChatColor.WHITE + player.getWorld().getName());
                player.sendMessage(ChatColor.AQUA + "Entity Count: " + ChatColor.WHITE + player.getWorld().getEntities().size());
            }

            sender.sendMessage(ChatColor.AQUA + "Number of Worlds: " + ChatColor.WHITE + this.plugin.getServer().getWorlds().size());
            sender.sendMessage(ChatColor.AQUA + "Total Entities: " + ChatColor.WHITE + this.plugin.getServer().getWorlds().stream().mapToInt(w -> w.getEntities().size()).sum());
            sender.sendMessage(ChatColor.AQUA + "Number of Plugins: " + ChatColor.WHITE + this.plugin.getServer().getPluginManager().getPlugins().length);
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        }

        return true;
    }
}
