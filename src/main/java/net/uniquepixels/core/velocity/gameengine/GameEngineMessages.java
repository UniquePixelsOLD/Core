package net.uniquepixels.core.velocity.gameengine;

import com.velocitypowered.api.proxy.ProxyServer;
import net.uniquepixels.core.velocity.gameengine.channel.RegisterGameServerChannel;
import net.uniquepixels.core.velocity.messaging.MessageChannelHandler;

public class GameEngineMessages {

    private final GameEngineRegister register = new GameEngineRegister();
    private final MessageChannelHandler channelHandler;
    private final ProxyServer proxyServer;

    public GameEngineMessages(MessageChannelHandler channelHandler, ProxyServer proxyServer) {
        this.channelHandler = channelHandler;
        this.proxyServer = proxyServer;
        setupChannel();
    }

    private void setupChannel() {

        this.channelHandler.registerChannel(new RegisterGameServerChannel(register, proxyServer));

    }


}
