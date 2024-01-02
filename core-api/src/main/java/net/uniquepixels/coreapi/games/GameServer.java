package net.uniquepixels.coreapi.games;

import java.util.UUID;

public record GameServer(UUID uid, GameType type, int currentPlayers, int maxPlayers) {
}
