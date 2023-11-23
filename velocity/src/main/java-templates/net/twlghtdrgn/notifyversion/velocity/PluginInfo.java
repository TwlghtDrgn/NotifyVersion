package net.twlghtdrgn.notifyversion.velocity;

import lombok.Getter;

@Getter
public class PluginInfo {
    private PluginInfo() {}

    public static final String VERSION = "${parent.version}";
}
