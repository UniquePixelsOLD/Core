package net.uniquepixels.core.velocity.messaging;

import com.velocitypowered.api.event.connection.PluginMessageEvent;
import org.intellij.lang.annotations.Subst;

public interface MessageChannel {

    @Subst("")
    String id();

    void event(PluginMessageEvent event);
}
