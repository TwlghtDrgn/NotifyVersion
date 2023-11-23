package net.twlghtdrgn.notifyversion.velocity.event;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PostLoginEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.proxy.Player;
import net.twlghtdrgn.notifyversion.common.IPlayer;
import net.twlghtdrgn.notifyversion.velocity.NotificationPlayerImpl;
import net.twlghtdrgn.notifyversion.velocity.NotifyVersionVelocity;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class ServerConnectListener {
    private final NotifyVersionVelocity plugin;
    public ServerConnectListener(NotifyVersionVelocity plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void onServerConnection(@NotNull PostLoginEvent event) {
        Player proxyPlayer = event.getPlayer();
        plugin.getServer().getScheduler().buildTask(plugin, task -> {
            if (proxyPlayer.getPendingResourcePack() != null) return;

            IPlayer internal = NotificationPlayerImpl.convertPlayer(proxyPlayer);
            plugin.getNotificationManager().sendBrandMessage(internal);
            task.cancel();
        }).delay(5L, TimeUnit.SECONDS)
                .repeat(1L, TimeUnit.SECONDS)
                .schedule();
    }

    @Subscribe
    public void onServerConnected(@NotNull ServerConnectedEvent event) {
        Player proxyPlayer = event.getPlayer();
        plugin.getServer().getScheduler().buildTask(plugin, task -> {
            if (proxyPlayer.getPendingResourcePack() != null) return;

            IPlayer internal = NotificationPlayerImpl.convertPlayer(proxyPlayer);
            plugin.getNotificationManager().sendVersionedString(internal);
            task.cancel();
        }).delay(5L, TimeUnit.SECONDS)
                .repeat(1L, TimeUnit.SECONDS)
                .schedule();
    }
}
