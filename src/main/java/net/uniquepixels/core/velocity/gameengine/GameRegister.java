package net.uniquepixels.core.velocity.gameengine;

import io.javalin.http.Context;
import io.javalin.websocket.WsConfig;
import net.uniquepixels.coreapi.games.GameServer;
import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.*;

public class GameRegister {


    private final CommunicationManager communicationManager;
    private final Map<UUID, List<Session>> sessions = new HashMap<>();

    public GameRegister(CommunicationManager communicationManager) {

        this.communicationManager = communicationManager;
    }

    private Optional<GameServer> findGameById(UUID uuid) {
        return this.communicationManager.getRunningServers().stream().filter(game -> game.uid().equals(uuid)).findFirst();
    }

    public void addServer(Context ctx) {
        GameServer gameServer = ctx.bodyAsClass(GameServer.class);
        this.communicationManager.getRunningServers().add(gameServer);
        ctx.json("added");
    }


    public void removeServer(Context ctx) throws IOException {

        String gameId = ctx.queryParam("game-id");

        Optional<GameServer> gameById = this.findGameById(UUID.fromString(gameId));

        if (gameById.isEmpty()) {

            ctx.res().sendError(404, "Server can't be found!");

            return;
        }

        this.communicationManager.getRunningServers().remove(gameById.get());
        ctx.json("removed");
    }

    private void addSession(UUID id, Session session) {

        if (this.sessions.containsKey(id)) {

            this.sessions.get(id).add(session);
            return;
        }

        this.sessions.put(id, new ArrayList<>(List.of(session)));
    }

    private void removeSession(UUID id, Session session) {

        if (this.sessions.containsKey(id))
            return;

        List<Session> list = this.sessions.get(id);

        list.remove(session);

        if (list.isEmpty()) {
            this.sessions.remove(id);
            return;
        }

        this.sessions.put(id, list);
    }

    public void updates(WsConfig ctx) {

        ctx.onConnect(wsConnectContext -> {

            String gameId = wsConnectContext.pathParam("game-id");

            this.addSession(UUID.fromString(gameId), wsConnectContext.session);

        });

        ctx.onClose(wsCloseContext -> {

            String gameId = wsCloseContext.pathParam("game-id");

            this.removeSession(UUID.fromString(gameId), wsCloseContext.session);
        });

        ctx.onMessage(wsMessageContext -> {

            GameServer gameServer = wsMessageContext.messageAsClass(GameServer.class);

            Optional<GameServer> gameById = this.findGameById(gameServer.uid());

            if (gameById.isEmpty()) {
                return;
            }

            this.communicationManager.getRunningServers().remove(gameById.get());
            this.communicationManager.getRunningServers().add(gameServer);

        });

    }
}
