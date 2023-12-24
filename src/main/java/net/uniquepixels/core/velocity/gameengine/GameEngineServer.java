package net.uniquepixels.core.velocity.gameengine;

import com.velocitypowered.api.proxy.server.RegisteredServer;

import java.util.Optional;

public record GameEngineServer(String address, String[] games, Optional<RegisteredServer> optionalRegisteredServer) {
}
