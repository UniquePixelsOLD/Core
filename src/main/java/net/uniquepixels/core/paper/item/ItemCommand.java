package net.uniquepixels.core.paper.item;

import dev.s7a.base64.Base64ItemStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.uniquepixels.core.paper.TextStyle;
import net.uniquepixels.core.paper.gui.UISlot;
import net.uniquepixels.core.paper.gui.UIStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            return true;
        }

        if (!player.hasPermission("uniquepixel.core.item")) {
            player.sendMessage(TextStyle.PREFIX.append(Component.translatable("permission").color(NamedTextColor.RED)));
            return true;
        }

        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if (itemInMainHand == null || !itemInMainHand.getType().isItem()) {
            player.sendMessage(TextStyle.PREFIX.append(Component.translatable("item.not.item").color(NamedTextColor.RED)));
            return true;
        }

        String encode = Base64ItemStack.encode(itemInMainHand);

        player.sendMessage(TextStyle.PREFIX
                .append(Component.translatable("item.encoded").color(TextStyle.PRIMARY_COLOR))
                .append(Component.space())
                .append(Component.translatable("item.code").color(TextStyle.HIGHLIGHT_COLOR)
                        .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, encode))));

        return true;
    }
}
