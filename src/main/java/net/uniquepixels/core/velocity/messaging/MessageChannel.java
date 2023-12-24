package net.uniquepixels.core.velocity.messaging;

import com.velocitypowered.api.event.connection.PluginMessageEvent;

public interface MessageChannel {

    String id();

    void event(PluginMessageEvent event);
}
