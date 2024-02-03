package net.uniquepixels.core.paper.gui.backend;

import net.uniquepixels.core.paper.gui.UIReference;
import net.uniquepixels.core.paper.gui.UIType;
import net.uniquepixels.core.paper.gui.types.chest.ChestUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UIHolder implements Listener {

    private final Map<Player, UIReference> openUIs = new HashMap<>();

    public synchronized void open(UIReference reference, Player player) {
        openUIs.put(player, reference);
        reference.open(player);
    }

    public boolean isPlayerNotInUI(Player player) {
        return !openUIs.containsKey(player);
    }

    public UIReference getUi(Player player) {
        if (isPlayerNotInUI(player))
            return null;

        return openUIs.get(player);
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        Player player = (Player) event.getViewers().get(0);

        if (isPlayerNotInUI(player))
            return;

        event.getInventory().setRepairCost(0);
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onInventoryClose(InventoryCloseEvent event) {

        if (!(event.getPlayer() instanceof Player player))
            return;

        if (isPlayerNotInUI(player))
            return;

        UIReference uiReference = getUi(player);

        if (Objects.requireNonNull(uiReference.type()) == UIType.CHEST) {
            ChestUI ui = (ChestUI) uiReference;

            ui.onClose(player);

            if (event.getInventory().getHolder() == ui.getInventory().getHolder())
                openUIs.remove(player);
        }

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClick(@NotNull InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player player))
            return;

        if (isPlayerNotInUI(player))
            return;

        UIReference uiReference = getUi(player);

        if (Objects.requireNonNull(uiReference.type()) == UIType.CHEST) {
            ChestUI ui = (ChestUI) uiReference;

            if (event.getInventory().getHolder() != ui.getInventory().getHolder())
                return;

            if (event.getClickedInventory() == null)
                return;

            if (ui.allowItemMovementInOtherInventories())
                return;

            ui.getItemsMap().forEach((uiItem, uiAction) -> {

                if (uiItem.getCurrentSlot().getSlot() != event.getSlot())
                    return;

                event.setCancelled(uiAction.onItemClick(player, uiItem, event.getClick(), event));

            });
        }

    }

}
