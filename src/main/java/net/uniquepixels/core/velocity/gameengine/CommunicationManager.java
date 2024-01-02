package net.uniquepixels.core.velocity.gameengine;

import io.javalin.Javalin;
import net.uniquepixels.coreapi.games.GameServer;

import java.util.ArrayList;
import java.util.List;

public class CommunicationManager {

    public List<GameServer> getRunningServers() {
        return runningServers;
    }

    private final List<GameServer> runningServers = new ArrayList<>();

    public CommunicationManager() {

        Javalin javalin = Javalin.create();

        GameRegister register = new GameRegister(this);

        javalin.post("/games/add", register::addServer);
        javalin.delete("/games/remove", register::removeServer);

        javalin.ws("/games/{game-id}", register::updates);

        javalin.start(50);

    }
}
