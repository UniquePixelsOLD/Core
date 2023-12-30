package net.uniquepixels.core.paper.gui.exception;

import net.uniquepixels.core.paper.gui.UIReference;
import net.uniquepixels.core.paper.gui.item.UIItem;
import net.uniquepixels.core.paper.gui.UIRow;

public class OutOfInventoryException extends Throwable {
  public OutOfInventoryException(UIReference reference, UIItem item, UIRow inventoryRow) {
    super(reference.type() + " | illegal item! specified item slot is higher then the inventory size. Item requires size of " + item.getOriginSlot().getParent().name() + " instead got " + inventoryRow.name());
  }
}
