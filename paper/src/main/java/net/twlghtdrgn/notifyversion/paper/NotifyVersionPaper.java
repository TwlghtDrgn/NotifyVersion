package net.twlghtdrgn.notifyversion.paper;

import lombok.Getter;
import net.twlghtdrgn.notifyversion.common.INotifyVersion;
import net.twlghtdrgn.notifyversion.common.config.Config;
import net.twlghtdrgn.notifyversion.common.manager.NotificationManager;
import net.twlghtdrgn.notifyversion.paper.command.ReloadCommand;
import net.twlghtdrgn.notifyversion.paper.event.PlayerJoinListener;
import net.twlghtdrgn.twilightlib.TwilightPlugin;
import net.twlghtdrgn.twilightlib.api.config.Configuration;

@Getter
public final class NotifyVersionPaper extends TwilightPlugin implements INotifyVersion {
    private Configuration<Config> conf;
    private NotificationManager notificationManager;

    @Override
    protected void enable() {
        conf = new Configuration<>(this, Config.class);
        if (!reload()) throw new IllegalStateException("Unable to load configuration file");
        notificationManager = new NotificationManager(this);

        getServer().getCommandMap().register("notifyversion", new ReloadCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    @Override
    protected void disable() {
        // unused
    }
}
