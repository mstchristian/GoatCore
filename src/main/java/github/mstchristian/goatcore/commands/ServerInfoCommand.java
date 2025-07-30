//Copyright (c) 2025 mst.christian_
//Licensed under the MIT License. See LICENSE file in the project root for full license information.

package github.mstchristian.goatcore.commands;

import github.mstchristian.goatcore.GoatCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerInfoCommand implements CommandExecutor {
    private final GoatCore plugin;

    private static final String LINE = ChatColor.GRAY + "-----";

    public ServerInfoCommand(GoatCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("goatcore.serverinfo")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
            return true;
        }

        var config = plugin.getConfig();
        var server = plugin.getServer();

        String serverName = config.getString("serverName", null);

        sendHeader(sender, "Server Information");

        if (serverName != null && !serverName.isEmpty()) {
            sender.sendMessage(ChatColor.AQUA + "Server Name: " + ChatColor.WHITE + serverName);
        }
        sender.sendMessage(ChatColor.AQUA + "Server True Name: " + ChatColor.WHITE + server.getName());
        sender.sendMessage(ChatColor.AQUA + "MOTD: " + ChatColor.WHITE + server.getMotd());
        sender.sendMessage(ChatColor.AQUA + "GoatCore Version: " + ChatColor.WHITE + plugin.getDescription().getVersion());
        sender.sendMessage(ChatColor.AQUA + "Server Version: " + ChatColor.WHITE + server.getVersion());
        sender.sendMessage(ChatColor.AQUA + "Bukkit Version: " + ChatColor.WHITE + server.getBukkitVersion());
        sender.sendMessage(ChatColor.AQUA + "Online Mode: " + ChatColor.WHITE + server.getOnlineMode());
        sender.sendMessage(ChatColor.AQUA + "Whitelist Enabled: " + ChatColor.WHITE + server.hasWhitelist());
        sender.sendMessage(ChatColor.AQUA + "View Distance: " + ChatColor.WHITE + server.getViewDistance());
        sender.sendMessage(ChatColor.AQUA + "Server IP: " + ChatColor.WHITE + server.getIp());
        sender.sendMessage(ChatColor.AQUA + "Server Port: " + ChatColor.WHITE + server.getPort());
        sender.sendMessage(ChatColor.AQUA + "Server Date: " + ChatColor.WHITE + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        sendHeader(sender, "Uptime & Status");

        sender.sendMessage(ChatColor.AQUA + "Server Uptime: " + ChatColor.WHITE + formatUptime(plugin.getStartTime()));
        sender.sendMessage(ChatColor.AQUA + "Online Players: " + ChatColor.WHITE + server.getOnlinePlayers().size());
        sender.sendMessage(ChatColor.AQUA + "Max Players: " + ChatColor.WHITE + server.getMaxPlayers());
        sender.sendMessage(ChatColor.AQUA + "Number of Plugins: " + ChatColor.WHITE + server.getPluginManager().getPlugins().length);

        sendHeader(sender, "System");

        Runtime runtime = Runtime.getRuntime();
        long usedMemMB = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
        long freeMemMB = runtime.freeMemory() / (1024 * 1024);
        long maxMemMB = runtime.maxMemory() / (1024 * 1024);
        double memUsagePercent = (double) (runtime.totalMemory() - runtime.freeMemory()) / runtime.maxMemory() * 100;

        sender.sendMessage(ChatColor.AQUA + "Java Version: " + ChatColor.WHITE + System.getProperty("java.version"));
        sender.sendMessage(ChatColor.AQUA + "Java Vendor: " + ChatColor.WHITE + System.getProperty("java.vendor"));
        sender.sendMessage(ChatColor.AQUA + "OS Name: " + ChatColor.WHITE + System.getProperty("os.name"));
        sender.sendMessage(ChatColor.AQUA + "CPU Cores: " + ChatColor.WHITE + runtime.availableProcessors());
        sender.sendMessage(ChatColor.AQUA + "Used Memory: " + ChatColor.WHITE + usedMemMB + " MB");
        sender.sendMessage(ChatColor.AQUA + "Free Memory: " + ChatColor.WHITE + freeMemMB + " MB");
        sender.sendMessage(ChatColor.AQUA + "Memory Usage: " + ChatColor.WHITE + String.format("%.2f", memUsagePercent) + "%");
        sender.sendMessage(ChatColor.AQUA + "Max Memory: " + ChatColor.WHITE + maxMemMB + " MB");

        sendHeader(sender, "Worlds");

        sender.sendMessage(ChatColor.AQUA + "Number of Worlds: " + ChatColor.WHITE + server.getWorlds().size());
        sender.sendMessage(ChatColor.AQUA + "Total Entities: " + ChatColor.WHITE + server.getWorlds().stream().mapToInt(w -> w.getEntities().size()).sum());
        sender.sendMessage(ChatColor.AQUA + "Loaded Chunks: " + ChatColor.WHITE + server.getWorlds().stream().mapToInt(w -> w.getLoadedChunks().length).sum());

        return true;
    }

    private void sendHeader(CommandSender sender, String title) {
        sender.sendMessage(LINE + ChatColor.YELLOW + " " + title + " " + LINE);
    }

    private String formatUptime(long startTimeMillis) {
        long uptimeSeconds = (System.currentTimeMillis() - startTimeMillis) / 1000;
        long days = uptimeSeconds / 86400;
        uptimeSeconds %= 86400;
        long hours = uptimeSeconds / 3600;
        uptimeSeconds %= 3600;
        long minutes = uptimeSeconds / 60;
        long seconds = uptimeSeconds % 60;

        return String.format("%dD %dH %dM %dS", days, hours, minutes, seconds);
    }
}
