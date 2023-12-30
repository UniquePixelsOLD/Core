package net.uniquepixels.core.velocity.messaging.impl;

import com.google.common.io.ByteArrayDataInput;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.uniquepixels.core.velocity.messaging.MessageChannel;

import java.util.Optional;

public class MessageAcrossNetwork implements MessageChannel {

    private final ProxyServer proxy;

    public MessageAcrossNetwork(ProxyServer proxy) {
        this.proxy = proxy;
    }

    @Override
    public String id() {
        return "message";
    }

    @Override
    public void event(PluginMessageEvent event) {

        ByteArrayDataInput data = event.dataAsDataStream();

        String uid = data.readUTF();
        String message = data.readUTF();

        TextComponent deserialize = LegacyComponentSerializer.legacyAmpersand().deserialize(message);

        Optional<Player> optional = this.proxy.getPlayer(uid);
        if (optional.isEmpty())
            return;

        Player player = optional.get();

        player.sendMessage(deserialize);
    }
}
