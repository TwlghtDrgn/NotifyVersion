package net.twlghtdrgn.notifyversion.Events;

import net.twlghtdrgn.notifyversion.NotifyVersion;
import net.twlghtdrgn.notifyversion.Utils.ViaConnector;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class PlayerJoinListener implements Listener {
    private final NotifyVersion notifyVersion;
    public PlayerJoinListener(NotifyVersion notifyVersion) {
        this.notifyVersion = notifyVersion;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ViaConnector via = new ViaConnector();
        Player player = event.getPlayer();

        int ver = via.getVersion(player);
        BukkitRunnable chat = new BukkitRunnable() {
            @Override
            public void run() {
                if (checkVersion(ver).equals("eol")) {

                    if (notifyVersion.getConfig().getBoolean("message-sound.enabled")) {
                        try {
                            player.playSound(player.getLocation(), Sound.valueOf(notifyVersion.getConfig().getString("message-sound.sound.EOL")), 1f, 1f);
                        } catch (IllegalArgumentException e) {
                            notifyVersion.getLogger().warning("Incorrect sound value: " + e);
                        }
                    }

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            notifyVersion.getConfig().getString("messages.prefix") + " "
                                    + notifyVersion.getConfig().getString("messages.EOL")));
                } else if (checkVersion(ver).equals("unsupported")) {

                    if (notifyVersion.getConfig().getBoolean("message-sound.enabled")) {
                        try {
                            player.playSound(player.getLocation(), Sound.valueOf(notifyVersion.getConfig().getString("message-sound.sound.Unsupported")), 1f, 1f);
                        } catch (IllegalArgumentException e) {
                            notifyVersion.getLogger().warning("Incorrect sound value: " + e);
                        }
                    }

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            notifyVersion.getConfig().getString("messages.prefix") + " "
                                    + notifyVersion.getConfig().getString("messages.Unsupported")));
                }
                this.cancel();
            }
        };

        try {
            chat.runTaskLater(notifyVersion, notifyVersion.getConfig().getLong("message-delay") * 20);
        } catch (IllegalArgumentException e) {
            notifyVersion.getLogger().warning("Incorrect time value: " + e);
        }
    }

    public String checkVersion(int ver) {
        List<Integer> eol = notifyVersion.getConfig().getIntegerList("versions.EOL");
        List<Integer> unsupported = notifyVersion.getConfig().getIntegerList("versions.Unsupported");

        for (int i: eol) {
            if (i == ver) {
                return "eol";
            }
        }

        for (int i: unsupported) {
            if (i == ver) {
                return "unsupported";
            }
        }

        return "";
    }
}
