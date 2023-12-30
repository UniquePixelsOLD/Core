package net.uniquepixels.core.paper.gui.backend;

import lombok.val;
import net.uniquepixels.core.paper.gui.UIReference;
import net.uniquepixels.core.paper.gui.UIType;
import net.uniquepixels.core.paper.gui.types.chest.ChestUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
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
        val player = (Player) event.getViewers().get(0);

        if (isPlayerNotInUI(player))
            return;

        event.getInventory().setRepairCost(0);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        if (!(event.getPlayer() instanceof Player player))
            return;

        if (isPlayerNotInUI(player))
            return;

        val uiReference = getUi(player);

        if (Objects.requireNonNull(uiReference.type()) == UIType.CHEST) {
            val ui = (ChestUI) uiReference;

            if (event.getInventory().getHolder() == ui.getInventory().getHolder())
                openUIs.remove(player);
        }

    }

    @EventHandler
    public void onInventoryClick(@NotNull InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player player))
            return;

        if (isPlayerNotInUI(player))
            return;

        val uiReference = getUi(player);

        if (Objects.requireNonNull(uiReference.type()) == UIType.CHEST) {
            val ui = (ChestUI) uiReference;

            if (event.getInventory().getHolder() != ui.getInventory().getHolder())
                return;

            if (event.getClickedInventory() == null)
                return;

            if (!ui.allowItemMovementInOtherInventories())
                if (event.getClickedInventory().getHolder() != ui.getInventory().getHolder())
                    return;

            ui.getItemsMap().forEach((uiItem, uiAction) -> {

                if (uiItem.getCurrentSlot().getSlot() != event.getSlot())
                    return;

                event.setCancelled(uiAction.onItemClick(player, uiItem, event.getClick(), event));

            });
        }

    }

}
