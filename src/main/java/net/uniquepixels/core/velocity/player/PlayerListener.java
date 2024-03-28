package net.uniquepixels.core.velocity.player;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.proxy.Player;

import java.util.Optional;

public class PlayerListener {

    private final PlayerManager playerManager;

    public PlayerListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Subscribe
    public void onConnect(ServerConnectedEvent event) {
        Player player = event.getPlayer();
        this.playerManager.createNetworkPlayer(player.getUniqueId(), player.getEffectiveLocale());
        this.playerManager.changeOnlineStatus(player.getUniqueId(), true);
    }

    @Subscribe
    public void onDisconnect(DisconnectEvent event) {
        Player player = event.getPlayer();
        this.playerManager.changeOnlineStatus(player.getUniqueId(), false);
    }

}
