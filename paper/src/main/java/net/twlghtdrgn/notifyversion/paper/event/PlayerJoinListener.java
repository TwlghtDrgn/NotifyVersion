package net.twlghtdrgn.notifyversion.paper.event;

import net.twlghtdrgn.notifyversion.common.IPlayer;
import net.twlghtdrgn.notifyversion.paper.NotificationPlayerImpl;
import net.twlghtdrgn.notifyversion.paper.NotifyVersionPaper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class PlayerJoinListener implements Listener {
    private final NotifyVersionPaper plugin;
    public PlayerJoinListener(NotifyVersionPaper plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Player bukkitPlayer = event.getPlayer();
        Bukkit.getAsyncScheduler().runAtFixedRate(plugin, task -> {
            if (bukkitPlayer.getResourcePackStatus() != null
                    && bukkitPlayer.getResourcePackStatus() != PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) return;

            IPlayer internal = NotificationPlayerImpl.convertPlayer(bukkitPlayer);
            plugin.getNotificationManager().sendBrandMessage(internal);
            plugin.getNotificationManager().sendVersionedString(internal);
            task.cancel();
        }, 5L, 1L, TimeUnit.SECONDS);
    }
}
