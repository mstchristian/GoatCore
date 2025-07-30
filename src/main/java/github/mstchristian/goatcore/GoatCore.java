//Copyright (c) 2025 mst.christian_
//Licensed under the MIT License. See LICENSE file in the project root for full license information.

package github.mstchristian.goatcore;

import github.mstchristian.goatcore.commands.GoatCoreCommand;
import github.mstchristian.goatcore.commands.ServerInfoCommand;
import github.mstchristian.goatcore.listeners.PlayerJoinListener;
import github.mstchristian.goatcore.listeners.PlayerQuitListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class GoatCore extends JavaPlugin {
    private long startTime;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        getCommand("goatcore").setExecutor(new GoatCoreCommand(this));
        getCommand("serverinfo").setExecutor(new ServerInfoCommand(this));

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);

        System.out.println("GoatCore has been enabled!");

        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void onDisable() {
        System.out.println("GoatCore has been disabled!");
    }

    public long getStartTime() {
        return startTime;
    }
}
