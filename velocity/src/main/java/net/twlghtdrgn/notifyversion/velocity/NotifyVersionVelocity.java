package net.twlghtdrgn.notifyversion.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;
import net.twlghtdrgn.notifyversion.common.INotifyVersion;
import net.twlghtdrgn.notifyversion.common.config.Config;
import net.twlghtdrgn.notifyversion.common.manager.NotificationManager;
import net.twlghtdrgn.notifyversion.velocity.command.ReloadCommand;
import net.twlghtdrgn.notifyversion.velocity.event.ServerConnectListener;
import net.twlghtdrgn.twilightlib.api.ILibrary;
import net.twlghtdrgn.twilightlib.api.config.ConfigLoader;
import net.twlghtdrgn.twilightlib.api.config.Configuration;
import net.twlghtdrgn.twilightlib.api.util.PluginInfoProvider;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "notifyversion",
        name = "NotifyVersion-Velocity",
        version = PluginInfo.VERSION,
        authors = {"TwlghtDrgn"},
        dependencies = {@Dependency(id = "twilightlib")}
)
public class NotifyVersionVelocity implements ILibrary, INotifyVersion {
    private final Logger log;
    @Getter
    private final ProxyServer server;
    @Getter
    private final Path path;
    @Getter
    private final PluginInfoProvider pluginInfo;
    @Getter
    private final ConfigLoader configLoader;
    @Getter
    private Configuration<Config> conf;
    @Getter
    private NotificationManager notificationManager;

    @Inject
    public NotifyVersionVelocity(@NotNull ProxyServer server, Logger logger, @DataDirectory Path path) {
        this.server = server;
        this.log = logger;
        this.path = path;
        this.pluginInfo = new PluginInfoProvider(
                "NotifyVersion",
                PluginInfo.VERSION,
                server.getVersion().getName() + " - " + server.getVersion().getVersion(),
                "https://github.com/TwlghtDrgn/NotifyVersion");
        this.configLoader = new ConfigLoader(this);
    }

    @Subscribe
    public void onProxyInitialization(@NotNull ProxyInitializeEvent event) {
        log().info(pluginInfo.getStartupMessage());
        try {
            conf = new Configuration<>(this, Config.class);
            if (!reload()) throw new IllegalStateException("Unable to load config");
            notificationManager = new NotificationManager(this);

            server.getCommandManager().register(
                    server.getCommandManager().metaBuilder("notifyversion")
                            .plugin(this)
                            .build(),
                    new ReloadCommand(this));
            server.getEventManager().register(this, new ServerConnectListener(this));
        } catch (Exception e) {
            log().error("Unable to load plugin properly", e);
        }
    }

    @Override
    public Logger log() {
        return log;
    }
}
