package net.uniquepixels.core.paper.chat.chatinput;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChatInputManager implements Listener {

    private final List<ChatInput> openInputs = new ArrayList<>();

    public void addChatInput(ChatInput chatInput) {
        this.openInputs.add(chatInput);
    }

    private Optional<ChatInput> getFromPlayer(Player player) {
        return this.openInputs.stream().filter(chatInput -> chatInput.player().equals(player)).findFirst();
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onAsyncChat(AsyncChatEvent event) {
        Player player = event.getPlayer();

        Optional<ChatInput> optional = this.getFromPlayer(player);

        if (optional.isEmpty())
            return;

        ChatInput chatInput = optional.get();

        chatInput.onDeliver().accept(event.originalMessage());
        event.setCancelled(true);
        this.openInputs.remove(chatInput);
    }
}
