//Copyright (c) 2025 mst.christian_
//Licensed under the MIT License. See LICENSE file in the project root for full license information.

package github.mstchristian.goatcore.listeners;

import github.mstchristian.goatcore.GoatCore;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final GoatCore plugin;

    public PlayerQuitListener(GoatCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String serverName = this.plugin.getConfig().getString("serverName", "Unknown Server");

        if (!plugin.getConfig().getBoolean("sendQuitMessage")) {
            event.setQuitMessage(null);
            return;
        }

        String rawMessage = this.plugin.getConfig().getString("quitMessage");
        if (rawMessage == null || rawMessage.isEmpty()) {
            event.setQuitMessage(null);
            return;
        }

        String finalMessage = rawMessage
                .replace("%player%", event.getPlayer().getDisplayName())
                .replace("%servername%", serverName);

        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', finalMessage));
    }
}
