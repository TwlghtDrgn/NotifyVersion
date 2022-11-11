package net.twlghtdrgn.notifyversion;

import net.twlghtdrgn.notifyversion.Commands.ReloadCommand;
import net.twlghtdrgn.notifyversion.Events.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NotifyVersion extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            saveDefaultConfig();

            if (getConfig().getInt("config-version") != 0 ) {
                throw new RuntimeException("Config has breaking changes. Please, rename your current config and restart this server to generate a new config");
            }

            getConfig().options().copyDefaults(true);
            saveConfig();

            getLogger().info("\n" +
                    "|| NotifyVersion by TwlghtDrgn\n" +
                    "|| Plugin GitHub repo: https://github.com/TwlghtDrgn/NotifyVersion\n" +
                    "|| Support (#plugin-support): https://discord.gg/PddWSCDBhP\n" +
                    "|| Or you can write an Issue on GitHub, on any language you prefer");

            getCommand("notify-reload").setExecutor(new ReloadCommand(this));
            getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        } catch (Exception e) {
            getLogger().severe(String.format("Could not start plugin: %s", e.getMessage()));
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
