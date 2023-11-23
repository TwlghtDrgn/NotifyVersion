package net.twlghtdrgn.notifyversion.common.util;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProtocolVersion {
    MC_1_20_2("1.20.2",764),
    MC_1_20("1.20 - 1.20.1",763),
    MC_1_19_4("1.19.4",762),
    MC_1_19_3("1.19.3",761),
    MC_1_19_1("1.19.1 - 1.19.2",760),
    MC_1_19("1.19",759),
    MC_1_18_2("1.18.2",758),
    MC_1_18("1.18 - 1.18.1",757),
    MC_1_17_1("1.17.1", 756),
    MC_1_17("1.17", 755),
    MC_1_16_4("1.16.4 - 1.16.5", 754),
    MC_1_16_3("1.16.3", 753),
    MC_1_16_2("1.16.2", 751),
    MC_1_16_1("1.16.1", 736),
    MC_1_16("1.16", 735),
    UNKNOWN("Unknown", -1);

    private final String version;
    private final int protocol;
    ProtocolVersion(String version, int protocol) {
        this.version = version;
        this.protocol = protocol;
    }

    public static ProtocolVersion getProtocol(int protocol) {
        return Arrays.stream(values())
                .filter(protocolVersion -> protocolVersion.getProtocol() == protocol)
                .findFirst()
                .orElse(UNKNOWN);
    }
}
