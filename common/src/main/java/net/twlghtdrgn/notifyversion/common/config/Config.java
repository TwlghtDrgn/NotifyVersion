package net.twlghtdrgn.notifyversion.common.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.twlghtdrgn.notifyversion.common.util.ClientBrand;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigSerializable
public class Config {
    public Config() {
        messages.add(new VersionedMessage(
                764,
                764,
                "This version ({PLAYER_VERSION}) is unstable, so proceed with caution. Nearest recommended version: {STABLE}",
                false,
                "entity.zombie_villager.ambient",
                1f,
                1f
        ));
    }

    private boolean brandNotifications = false;
    private List<ClientBrand> recommendedBrands = new ArrayList<>(List.of(
            ClientBrand.QUILT,
            ClientBrand.FABRIC,
            ClientBrand.VANILLA
    ));
    private String brandMessage = "You are using an unsupported client ({PLAYER_BRAND}). This may lead to various graphical and gameplay issues. Recommended clients: {BRAND}";

    private int minRecommendedProtocol = 762;
    private int maxRecommendedProtocol = 763;
    private final List<VersionedMessage> messages = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ConfigSerializable
    public static class VersionedMessage {
        private int minProtocol = 0;
        private int maxProtocol = 0;
        private String message = "";
        private boolean playSound = false;
        private String sound = "";
        private float soundVolume = 1f;
        private float soundSpeed = 1f;
    }
}
