package net.uniquepixels.core.velocity.messaging;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;

import java.util.HashMap;
import java.util.Map;

public class MessageChannelHandler {

    private final ProxyServer server;
    private final Map<String, MessageChannel> identifierMap = new HashMap<>();

    public MessageChannelHandler(ProxyServer server) {
        this.server = server;
    }

    public void registerChannel(MessageChannel channel) {
        if (this.identifierMap.containsKey("minecraft: " + channel.id()))
            return;

        MinecraftChannelIdentifier identifier = MinecraftChannelIdentifier.forDefaultNamespace(channel.id());

        this.server.getChannelRegistrar().register(identifier);
        this.identifierMap.put(identifier.getId(), channel);
    }

    public void unregisterChannel(String channelKey) {
        if (this.identifierMap.containsKey(channelKey))
            return;

        this.server.getChannelRegistrar().unregister(MinecraftChannelIdentifier.from(this.identifierMap.get(channelKey).id()));
    }

    @Subscribe
    public void onPluginMessageEvent(PluginMessageEvent event) {

        String id = event.getIdentifier().getId();

        if (!this.identifierMap.containsKey(id))
            return;

        this.identifierMap.get(id).event(event);
    }

}
