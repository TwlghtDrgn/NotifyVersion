package net.twlghtdrgn.notifyversion.common.util;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

@Getter
public enum ClientBrand {
    VANILLA("Vanilla"),
    FORGE("Forge"),
    FABRIC("Fabric"),
    QUILT("Quilt"),
    OPTIFINE("Optifine"),
    UNKNOWN("Unknown");

    private final String brand;
    ClientBrand(String brand) {
        this.brand = brand;
    }

    public static ClientBrand getBrand(@Nullable String name) {
        if (name == null) return UNKNOWN;
        return Arrays.stream(values())
                .filter(clientBrand -> name.toLowerCase().contains(clientBrand.getBrand().toLowerCase()))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
