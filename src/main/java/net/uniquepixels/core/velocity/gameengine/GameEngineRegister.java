package net.uniquepixels.core.velocity.gameengine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameEngineRegister {

    private final Map<String, GameEngineServer> gameEngineServerMap = new HashMap<>();

    public void addGameEngine(GameEngineServer server) {
        this.gameEngineServerMap.put(server.address(), server);
    }

    public boolean isServerRegistered(String address) {
        return this.gameEngineServerMap.containsKey(address);
    }

    public Optional<GameEngineServer> getServer(String address) {
        if (this.isServerRegistered(address))
            return Optional.empty();

        return Optional.of(this.gameEngineServerMap.get(address));
    }

    public void removeGameServer(String address) {
        this.gameEngineServerMap.remove(address);
    }

}
