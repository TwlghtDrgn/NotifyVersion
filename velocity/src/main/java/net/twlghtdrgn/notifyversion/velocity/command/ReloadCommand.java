package net.twlghtdrgn.notifyversion.velocity.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import net.twlghtdrgn.notifyversion.common.PluginPermission;
import net.twlghtdrgn.notifyversion.velocity.NotifyVersionVelocity;
import net.twlghtdrgn.twilightlib.api.util.Format;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ReloadCommand implements SimpleCommand {
    private final NotifyVersionVelocity plugin;
    public ReloadCommand(NotifyVersionVelocity plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(@NotNull Invocation invocation) {
        final String[] args = invocation.arguments();
        final CommandSource source = invocation.source();
        if (args.length < 1) return;
        if (args[0].equals("reload")) {
            if (plugin.reload()) {
                source.sendMessage(Format.parse("<green>Config reloaded"));
            } else {
                source.sendMessage(Format.parse("<red>Config reload error"));
            }
            return;
        }
        source.sendMessage(Format.parse("<gray>Unknown subcommand"));
    }

    @Override
    public CompletableFuture<List<String>> suggestAsync(@NotNull Invocation invocation) {
        final String[] args = invocation.arguments();
        if (args.length <= 1) return CompletableFuture.completedFuture(List.of("reload"));
        return CompletableFuture.completedFuture(List.of());
    }

    @Override
    public boolean hasPermission(@NotNull Invocation invocation) {
        return invocation.source().hasPermission(PluginPermission.RELOAD);
    }
}
