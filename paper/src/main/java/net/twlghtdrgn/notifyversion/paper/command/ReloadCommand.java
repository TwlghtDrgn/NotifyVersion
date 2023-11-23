package net.twlghtdrgn.notifyversion.paper.command;

import net.twlghtdrgn.notifyversion.common.PluginPermission;
import net.twlghtdrgn.notifyversion.paper.NotifyVersionPaper;
import net.twlghtdrgn.twilightlib.api.util.Format;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ReloadCommand extends Command {
    private final NotifyVersionPaper plugin;
    public ReloadCommand(NotifyVersionPaper plugin) {
        super("notifyversion");
        this.plugin = plugin;
    }


    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
        if (args.length < 1) return false;
        if (args[0].equals("reload")) {
            if (plugin.reload()) {
                sender.sendMessage(Format.parse("<green>Config reloaded"));
                return true;
            } else {
                sender.sendMessage(Format.parse("<red>Config reload error"));
                return false;
            }
        }
        sender.sendMessage(Format.parse("<gray>Unknown subcommand"));
        return false;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String @NotNull [] args) throws IllegalArgumentException {
        if (args.length <= 1) return List.of("reload");
        return List.of();
    }

    @Override
    public boolean testPermission(@NotNull CommandSender target) {
        return target.hasPermission(PluginPermission.RELOAD);
    }
}
