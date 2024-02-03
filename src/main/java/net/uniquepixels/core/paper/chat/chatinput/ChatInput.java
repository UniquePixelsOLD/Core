package net.uniquepixels.core.paper.chat.chatinput;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public record ChatInput(Player player, Consumer<Component> onDeliver) {
}
