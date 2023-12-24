package net.uniquepixels.core.velocity.gameengine.channel;

import com.google.common.io.ByteArrayDataInput;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.uniquepixels.core.velocity.gameengine.GameEngineRegister;
import net.uniquepixels.core.velocity.gameengine.GameEngineServer;
import net.uniquepixels.core.velocity.messaging.MessageChannel;

import java.util.Optional;

public class RegisterGameServerChannel implements MessageChannel {
    private final GameEngineRegister register;
    private final ProxyServer server;

    public RegisterGameServerChannel(GameEngineRegister register, ProxyServer server) {

        this.register = register;
        this.server = server;
    }

    @Override
    public String id() {
        return "up-game-engine-register-server";
    }

    @Override
    public void event(PluginMessageEvent event) {

        ByteArrayDataInput byteArrayDataInput = event.dataAsDataStream();

        // server, games

        String serverName = byteArrayDataInput.readUTF();
        String games = byteArrayDataInput.readUTF();

        String[] splitGames = games.split(",");

        Optional<RegisteredServer> server1 = this.server.getServer(serverName);

        this.register.addGameEngine(new GameEngineServer(serverName, splitGames, server1));


        event.setResult(PluginMessageEvent.ForwardResult.handled());
    }
}
