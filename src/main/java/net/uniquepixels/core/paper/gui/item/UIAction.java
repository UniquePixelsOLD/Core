package net.uniquepixels.core.paper.gui.item;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface UIAction {

  boolean onItemClick(Player clicker, UIItem clickedItem, ClickType action, InventoryClickEvent event);

}
