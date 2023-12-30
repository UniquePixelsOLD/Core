package net.uniquepixels.core.paper.gui.types.chest;

import lombok.Getter;
import lombok.val;
import net.kyori.adventure.text.Component;
import net.uniquepixels.core.paper.gui.UIReference;
import net.uniquepixels.core.paper.gui.UIRow;
import net.uniquepixels.core.paper.gui.UISlot;
import net.uniquepixels.core.paper.gui.UIType;
import net.uniquepixels.core.paper.gui.background.UIBackground;
import net.uniquepixels.core.paper.gui.exception.OutOfInventoryException;
import net.uniquepixels.core.paper.gui.item.UIAction;
import net.uniquepixels.core.paper.gui.item.UIItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ChestUI implements InventoryHolder, UIReference {

    @Getter
    protected final Map<UIItem, UIAction> itemsMap = new HashMap<>();
    protected final UIRow rows;
    private final Component uiTitle;
    private final Inventory inventory;
    private UIBackground background;

    public ChestUI(Component uiTitle, UIRow rows, Player opener) {
        this.uiTitle = uiTitle;
        this.rows = rows;
        this.inventory = Bukkit.createInventory(this, rows.getSlots(), uiTitle);

        background = new UIBackground(UIBackground.BackgroundType.NONE, List.of());

        try {
            initItems(opener);
        } catch (OutOfInventoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void open(Player player) {
        try {
            refreshInventory();
        } catch (OutOfInventoryException e) {
            throw new RuntimeException(e);
        }
        player.openInventory(inventory);
    }

    protected abstract void initItems(Player opener) throws OutOfInventoryException;

    public void setBackground(UIBackground background) {
        this.background = background;
    }

    protected void refreshInventory() throws OutOfInventoryException {
        itemsMap.forEach((item, uiAction) -> this.inventory.setItem(item.getOriginSlot().getSlot(), item.buildItem()));

        for (int i = 0; i < this.rows.getSlots(); i++) {

            val item = inventory.getItem(i);

            if (background.type() == UIBackground.BackgroundType.NONE)
                continue;

            if (item == null) {

                switch (background.type()) {
                    case FULL -> {

                        val uiItem = background.backgroundItems().get(0);
                        val itemStack = uiItem.buildItem();

                        item(new UIItem(itemStack, UISlot.fromSlotId(i).get()), (clicker, clickedItem, action, event) -> true);

                        inventory.setItem(i, itemStack);

                    }
                    case SELF -> {

                        if (background.backgroundItems().size() < this.rows.getSlots()) {

                            if (i != 0) {

                                val uiItem = background.backgroundItems().get(0);
                                val itemStack = uiItem.buildItem();
                                item(new UIItem(itemStack, UISlot.fromSlotId(i).get()), (clicker, clickedItem, action, event) -> true);

                                inventory.setItem(i, itemStack);

                            }

                            throw new IllegalArgumentException("Not enough items for a self background in the list. Required: " + this.rows.getSlots() + " got: " + background.backgroundItems().size() + " items");

                        } else {
                            val uiItem = background.backgroundItems().get(i);
                            item(new UIItem(uiItem.buildItem(), UISlot.fromSlotId(i).get()), (clicker, clickedItem, action, event) -> true);
                        }

                    }
                }

            }

        }
    }

    public void item(UIItem item, UIAction click) throws OutOfInventoryException {

        if (!UIRow.isParentFrom(rows, item.getOriginSlot().getParent()))
            throw new OutOfInventoryException(this, item, rows);

        itemsMap.put(item, click);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    @Override
    public Component displayText() {
        return uiTitle;
    }

    @Override
    public UIType type() {
        return UIType.CHEST;
    }
}
