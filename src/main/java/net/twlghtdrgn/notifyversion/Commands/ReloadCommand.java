package net.twlghtdrgn.notifyversion.Commands;

import net.twlghtdrgn.notifyversion.NotifyVersion;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    private final NotifyVersion notifyVersion;

    public ReloadCommand(NotifyVersion notifyVersion) {
        this.notifyVersion = notifyVersion;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.GREEN + "Config reloaded");
        }

        notifyVersion.getLogger().info("Config reloaded");

        notifyVersion.reloadConfig();
        return true;
    }
}
