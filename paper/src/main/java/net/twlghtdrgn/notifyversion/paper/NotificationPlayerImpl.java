package net.twlghtdrgn.notifyversion.paper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.twlghtdrgn.notifyversion.common.IPlayer;
import net.twlghtdrgn.notifyversion.common.util.ClientBrand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class NotificationPlayerImpl implements IPlayer {
    private final UUID uniqueId;
    private final String nickname;
    private final Integer protocolVersion;
    private final ClientBrand clientBrand;
    private final Player bukkitPlayer;

    @Override
    public void sendMessage(@NotNull Component message) {
        bukkitPlayer.sendMessage(message);
    }

    @Override
    public void playSound(@NotNull Sound sound) {
        bukkitPlayer.playSound(sound, Sound.Emitter.self());
    }

    public static @NotNull IPlayer convertPlayer(@NotNull Player bukkitPlayer) {
        return new NotificationPlayerImpl(
                bukkitPlayer.getUniqueId(),
                bukkitPlayer.getName(),
                bukkitPlayer.getProtocolVersion(),
                ClientBrand.getBrand(bukkitPlayer.getClientBrandName()),
                bukkitPlayer);
    }
}
