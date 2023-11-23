package net.twlghtdrgn.notifyversion.common;

import net.twlghtdrgn.notifyversion.common.config.Config;
import net.twlghtdrgn.notifyversion.common.manager.NotificationManager;
import net.twlghtdrgn.twilightlib.api.ILibrary;
import net.twlghtdrgn.twilightlib.api.config.Configuration;
import org.spongepowered.configurate.ConfigurateException;

public interface INotifyVersion extends ILibrary {
    NotificationManager getNotificationManager();
    Configuration<Config> getConf();

    @Override
    default boolean reload() {
        try {
            getConf().reload();
            return true;
        } catch (ConfigurateException e) {
            log().error("Unable to reload config", e);
            return false;
        }
    }
}
