package net.uniquepixels.core.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import net.uniquepixels.core.velocity.messaging.MessageChannelHandler;
import net.uniquepixels.core.velocity.messaging.impl.MessageAcrossNetwork;
import net.uniquepixels.core.velocity.player.PlayerListener;
import net.uniquepixels.core.velocity.player.PlayerManager;
import net.uniquepixels.core.velocity.resourcepack.ResourcePackPlayerJoinListener;
import net.uniquepixels.core.velocity.resourcepack.management.ResourcePackHandler;
import org.slf4j.Logger;

@Plugin(
        id = "velocitycore",
        name = "VelocityCore",
        version = "latest",
        authors = {"DasShorty"}
)

public class VelocityCore {
    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public VelocityCore(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;

        logger.info("""
                 __      __  _            _ _          _____              \s
                 \\ \\    / / | |          (_) |        / ____|             \s
                  \\ \\  / /__| | ___   ___ _| |_ _   _| |     ___  _ __ ___\s
                   \\ \\/ / _ \\ |/ _ \\ / __| | __| | | | |    / _ \\| '__/ _ \\
                    \\  /  __/ | (_) | (__| | |_| |_| | |___| (_) | | |  __/
                     \\/ \\___|_|\\___/ \\___|_|\\__|\\__, |\\_____\\___/|_|  \\___|
                                                 __/ |                    \s
                                                |___/                     \s
                """);
    }

    public ProxyServer getServer() {
        return server;
    }

    public Logger getLogger() {
        return logger;
    }

    @Subscribe
    public void onProxyInitializeEvent(ProxyInitializeEvent event) {

        //resourcePack();
        MessageChannelHandler channelHandler = new MessageChannelHandler(this.server);

        channelHandler.registerChannel(new MessageAcrossNetwork(this.server));

        PlayerManager playerManager = new PlayerManager();
        this.server.getEventManager().register(this, new PlayerListener(playerManager));

    }

    private void resourcePack() {
        ResourcePackHandler resourcePackHandler = new ResourcePackHandler(this, this.server);
        this.server.getEventManager().register(this, new ResourcePackPlayerJoinListener(this.server, resourcePackHandler));
    }
}
