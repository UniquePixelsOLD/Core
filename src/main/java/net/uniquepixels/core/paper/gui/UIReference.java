package net.uniquepixels.core.paper.gui;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

public interface UIReference extends InventoryHolder {

  Component displayText();

  UIType type();

  /**
   * @return if true items can be moved in other inventories that the player has open
   * */
  boolean allowItemMovementInOtherInventories();

  void open(Player player);

}
