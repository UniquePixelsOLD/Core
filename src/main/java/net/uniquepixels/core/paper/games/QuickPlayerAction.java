package net.uniquepixels.core.paper.games;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.uniquepixels.core.paper.PaperCore;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public record QuickPlayerAction(Player player) {

    public void sendPlayerToGame(GameTypes game) {

        ByteArrayDataOutput data = ByteStreams.newDataOutput();

        data.writeUTF(player.getUniqueId().toString());
        data.writeUTF(game.name());

        PaperCore plugin = JavaPlugin.getPlugin(PaperCore.class);

        player.sendPluginMessage(plugin, "uniquepixels:up-send-player-to-game", data.toByteArray());

    }

    public void sendMessageAcrossNetwork(Component message) {

        ByteArrayDataOutput data = ByteStreams.newDataOutput();

        data.writeUTF(player.getUniqueId().toString());
        data.writeUTF(LegacyComponentSerializer.legacyAmpersand().serialize(message));

        PaperCore plugin = JavaPlugin.getPlugin(PaperCore.class);

        player.sendPluginMessage(plugin, "uniquepixels:message", data.toByteArray());
    }

}
