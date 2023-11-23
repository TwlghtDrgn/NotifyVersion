package net.twlghtdrgn.notifyversion.common.manager;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.twlghtdrgn.notifyversion.common.INotifyVersion;
import net.twlghtdrgn.notifyversion.common.IPlayer;
import net.twlghtdrgn.notifyversion.common.config.Config;
import net.twlghtdrgn.notifyversion.common.util.ClientBrand;
import net.twlghtdrgn.notifyversion.common.util.ProtocolVersion;
import net.twlghtdrgn.twilightlib.api.util.Format;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Collectors;

public class NotificationManager {
    private final INotifyVersion plugin;
    public NotificationManager(INotifyVersion plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("all")
    public void sendVersionedString(@NotNull IPlayer player) {
        final Config cfg = plugin.getConf().get();
        final Optional<Config.VersionedMessage> message = cfg.getMessages().stream()
                        .filter(versionedMessage ->
                                player.getProtocolVersion() >= versionedMessage.getMinProtocol()
                                && player.getProtocolVersion() <= versionedMessage.getMaxProtocol()
                        ).findFirst();
        if (message.isEmpty()) return;

        String stable = getNearestVersion(player);
        String raw = message.get().getMessage()
                .replace("{PLAYER_VERSION}", ProtocolVersion.getProtocol(player.getProtocolVersion()).getVersion())
                .replace("{STABLE}", stable);
        player.sendMessage(Format.parse(raw, player));

        if (!message.get().isPlaySound()) return;
        final Key key = Key.key(message.get().getSound());
        Sound sound = Sound.sound()
                .source(Sound.Source.MASTER)
                .type(key)
                .pitch(1f)
                .volume(1f)
                .build();
        player.playSound(sound);
    }

    public void sendBrandMessage(@NotNull IPlayer player) {
        final Config cfg = plugin.getConf().get();
        if (!cfg.isBrandNotifications()) return;
        if (player.getClientBrand() != null && cfg.getRecommendedBrands().contains(player.getClientBrand())) return;

        String brands = cfg.getRecommendedBrands().stream()
                .map(ClientBrand::getBrand)
                .collect(Collectors.joining(", "));
        String raw = cfg.getBrandMessage()
                .replace("{PLAYER_BRAND}", player.getClientBrand().getBrand())
                .replace("{BRAND}", brands);
        player.sendMessage(Format.parse(raw, player));
    }

    private String getNearestVersion(@NotNull IPlayer player) {
        final Config cfg = plugin.getConf().get();
        if (player.getProtocolVersion() < cfg.getMinRecommendedProtocol()) return ProtocolVersion.getProtocol(cfg.getMinRecommendedProtocol()).getVersion();
        if (player.getProtocolVersion() > cfg.getMaxRecommendedProtocol()) return ProtocolVersion.getProtocol(cfg.getMaxRecommendedProtocol()).getVersion();
        return ProtocolVersion.UNKNOWN.getVersion();
    }
}
