//Copyright (c) 2025 mst.christian_
//Licensed under the MIT License. See LICENSE file in the project root for full license information.

package github.mstchristian.goatcore.listeners;

import github.mstchristian.goatcore.GoatCore;
import org.bukkit.ChatColor;
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
        String serverName = this.plugin.getConfig().getString("serverName");
        if (serverName == null || serverName.isEmpty()) {
            serverName = "Unknown Server";
        }

        if (this.plugin.getConfig().getBoolean("sendFirstJoinMessage") && !event.getPlayer().hasPlayedBefore()) {
            String message = this.plugin.getConfig().getString("firstJoinMessage");
            if (message != null && !message.isEmpty()) {
                message = message
                        .replace("%player%", event.getPlayer().getDisplayName())
                        .replace("%servername%", serverName);
                event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
        } else if (this.plugin.getConfig().getBoolean("sendJoinMessage")) {
            String message = this.plugin.getConfig().getString("joinMessage");
            if (message != null && !message.isEmpty()) {
                message = message
                        .replace("%player%", event.getPlayer().getDisplayName())
                        .replace("%servername%", serverName);
                event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
        } else {
            event.setJoinMessage(null);
        }
    }
}
