//Copyright (c) 2025 mst.christian_
//Licensed under the MIT License. See LICENSE file in the project root for full license information.

package github.mstchristian.goatcore.listeners;

import github.mstchristian.goatcore.GoatCore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final GoatCore plugin;

    public PlayerJoinListener(GoatCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        var config = plugin.getConfig();
        var player = event.getPlayer();
        String serverName = config.getString("serverName");

        if (config.getBoolean("lobby.teleportOnJoin")) {
            Location lobbyLocation = config.getLocation("lobby.location");
            if (lobbyLocation != null) {
                player.teleport(lobbyLocation);
            }
        }

        if (!player.hasPlayedBefore() && config.getBoolean("sendFirstJoinMessage")) {
            String rawMessage = config.getString("firstJoinMessage");
            if (rawMessage != null && !rawMessage.isEmpty()) {
                event.setJoinMessage(formatMessage(rawMessage, player.getDisplayName(), serverName));
                return;
            }
        }

        if (config.getBoolean("sendJoinMessage")) {
            String rawMessage = config.getString("joinMessage");
            if (rawMessage != null && !rawMessage.isEmpty()) {
                event.setJoinMessage(formatMessage(rawMessage, player.getDisplayName(), serverName));
                return;
            }
        }

        event.setJoinMessage(null);
    }

    private String formatMessage(String raw, String playerName, String serverName) {
        return ChatColor.translateAlternateColorCodes('&', raw
                .replace("%player%", playerName)
                .replace("%servername%", serverName));
    }
}
