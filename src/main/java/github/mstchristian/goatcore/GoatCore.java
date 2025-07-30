//Copyright (c) 2025 mst.christian_
//Licensed under the MIT License. See LICENSE file in the project root for full license information.

package github.mstchristian.goatcore;

import github.mstchristian.goatcore.commands.GoatCoreCommand;
import github.mstchristian.goatcore.commands.LobbyCommand;
import github.mstchristian.goatcore.commands.ServerInfoCommand;
import github.mstchristian.goatcore.commands.SetLobbyCommand;
import github.mstchristian.goatcore.listeners.PlayerJoinListener;
import github.mstchristian.goatcore.listeners.PlayerQuitListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class GoatCore extends JavaPlugin {
    private long startTime;

    @Override
    public void onEnable() {
        saveDefaultPluginConfig();
        registerCommands();
        registerEvents();
        logPluginEnabled();

        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void onDisable() {
        getLogger().info("GoatCore has been disabled!");
    }

    public long getStartTime() {
        return startTime;
    }

    private void saveDefaultPluginConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    private void registerCommands() {
        getCommand("goatcore").setExecutor(new GoatCoreCommand(this));
        getCommand("serverinfo").setExecutor(new ServerInfoCommand(this));
        getCommand("setlobby").setExecutor(new SetLobbyCommand(this));
        getCommand("lobby").setExecutor(new LobbyCommand(this));
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
    }

    private void logPluginEnabled() {
        getLogger().info("GoatCore has been enabled!");
    }
}
