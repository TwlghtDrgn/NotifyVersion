package net.twlghtdrgn.notifyversion.velocity;

import com.velocitypowered.api.proxy.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.twlghtdrgn.notifyversion.common.IPlayer;
import net.twlghtdrgn.notifyversion.common.util.ClientBrand;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class NotificationPlayerImpl implements IPlayer {
    private final UUID uniqueId;
    private final String nickname;
    private final Integer protocolVersion;
    private final ClientBrand clientBrand;
    private final Player velocityPlayer;

    @Override
    public void sendMessage(@NotNull Component message) {
        velocityPlayer.sendMessage(message);
    }

    public static @NotNull IPlayer convertPlayer(@NotNull Player velocityPlayer) {
        return new NotificationPlayerImpl(
                velocityPlayer.getUniqueId(),
                velocityPlayer.getUsername(),
                velocityPlayer.getProtocolVersion().getProtocol(),
                ClientBrand.getBrand(velocityPlayer.getClientBrand()),
                velocityPlayer);
    }
}
