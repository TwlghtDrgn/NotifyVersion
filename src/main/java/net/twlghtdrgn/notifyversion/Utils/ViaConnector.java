package net.twlghtdrgn.notifyversion.Utils;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;
import org.bukkit.entity.Player;

public class ViaConnector {
    ViaAPI api = Via.getAPI();

    public int getVersion(Player player) {
        return api.getPlayerVersion(player);
    }
}
