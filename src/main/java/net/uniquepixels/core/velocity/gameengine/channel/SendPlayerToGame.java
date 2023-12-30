package net.uniquepixels.core.velocity.gameengine.channel;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import net.kyori.adventure.key.Key;
import net.uniquepixels.core.velocity.messaging.MessageChannel;

import java.util.Optional;
import java.util.UUID;

public class SendPlayerToGame implements MessageChannel {

    private final ProxyServer proxy;

    public SendPlayerToGame(ProxyServer proxy) {
        this.proxy = proxy;
    }

    @Override
    public String id() {
        return "up-send-player-to-game";
    }

    @Override
    public void event(PluginMessageEvent event) {

        // uid, game

        ByteArrayDataInput data = event.dataAsDataStream();

        String uid = data.readUTF();
        String game = data.readUTF();

        try {
            Optional<Player> optional = this.proxy.getPlayer(UUID.fromString(uid));


            if (optional.isEmpty())
                return;

            Player player = optional.get();

            ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
            byteArrayDataOutput.writeUTF(uid);
            byteArrayDataOutput.writeUTF(game);

            player.sendPluginMessage(MinecraftChannelIdentifier.from(Key.key("uniquepixels", id())), byteArrayDataOutput.toByteArray());

        } catch (IllegalArgumentException e) {
            e.fillInStackTrace();
        }


    }
}
