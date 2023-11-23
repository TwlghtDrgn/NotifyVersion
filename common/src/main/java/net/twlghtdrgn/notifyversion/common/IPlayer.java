package net.twlghtdrgn.notifyversion.common;

import net.kyori.adventure.audience.Audience;
import net.twlghtdrgn.notifyversion.common.util.ClientBrand;

import java.util.UUID;

public interface IPlayer extends Audience {
    UUID getUniqueId();
    String getNickname();
    Integer getProtocolVersion();
    ClientBrand getClientBrand();
}
