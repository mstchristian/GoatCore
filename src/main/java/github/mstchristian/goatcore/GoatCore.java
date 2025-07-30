//Copyright (c) 2025 mst.christian_
//Licensed under the MIT License. See LICENSE file in the project root for full license information.

package github.mstchristian.goatcore;

import github.mstchristian.goatcore.commands.GoatCoreCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class GoatCore extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("goatcore").setExecutor(new GoatCoreCommand(this));

        System.out.println("GoatCore has been enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("GoatCore has been disabled!");
    }
}
