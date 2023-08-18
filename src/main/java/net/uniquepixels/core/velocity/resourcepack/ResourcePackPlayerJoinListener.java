package net.uniquepixels.core.velocity.resourcepack;

import com.google.common.annotations.Beta;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.player.ServerPostConnectEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.player.ResourcePackInfo;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.uniquepixels.core.velocity.resourcepack.management.ResourcePackHandler;
import net.uniquepixels.core.velocity.resourcepack.management.ResourcePackVersion;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ResourcePackPlayerJoinListener {

    private final ProxyServer proxyServer;
    final List<UUID> playerUUIDs = new ArrayList<>();
    private final ResourcePackHandler resourcePackHandler;

    @Subscribe
    @Beta
    public void onPlayerConnect(ServerPostConnectEvent event) {
        Player player = event.getPlayer();

        if (playerUUIDs.contains(player.getUniqueId()))
            return;

        playerUUIDs.add(player.getUniqueId());

        val protocol = player.getProtocolVersion().getProtocol();
        ResourcePackVersion resourcePackVersion = ResourcePackVersion.getByProtocolVersion(protocol);

        assert resourcePackVersion != null;

        ResourcePackInfo.Builder resourcePackBuilder = this.proxyServer.createResourcePackBuilder(resourcePackHandler.getTexturePackFromVersion(resourcePackVersion));
        player.sendResourcePackOffer(resourcePackBuilder.setHash(resourcePackHandler.getResourcePackHashCodes().get(resourcePackVersion)).setShouldForce(true).build());
    }

    @Subscribe
    public void onPlayerLeaveProxy(DisconnectEvent event) {
        this.playerUUIDs.remove(event.getPlayer().getUniqueId());
    }

}
