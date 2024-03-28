package net.uniquepixels.core.paper.gui.types.chest;

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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ChestUI implements UIReference {

    protected final UIRow rows;
    private final Component uiTitle;
    private final Inventory inventory;
    protected Map<UIItem, UIAction> itemsMap = new HashMap<>();
    private UIBackground background;

    public ChestUI(Component uiTitle, UIRow rows) {
        this.uiTitle = uiTitle;
        this.rows = rows;
        this.inventory = Bukkit.createInventory(this, rows.getSlots(), uiTitle);
        background = new UIBackground(UIBackground.BackgroundType.NONE, List.of());
    }

    public Map<UIItem, UIAction> getItemsMap() {
        return itemsMap;
    }

    public UIRow getRows() {
        return rows;
    }

    public Component getUiTitle() {
        return uiTitle;
    }

    public UIBackground getBackground() {
        return background;
    }

    public void setBackground(UIBackground background) {
        this.background = background;
    }

    @Override
    public void open(Player player) {
        try {
            refreshInventory(player);
        } catch (OutOfInventoryException e) {
            throw new RuntimeException(e);
        }
        player.openInventory(inventory);
    }

    protected abstract void initItems(Player opener) throws OutOfInventoryException;

    public abstract void onClose(Player player);

    protected void refreshInventory(Player player) throws OutOfInventoryException {
        this.itemsMap = new HashMap<>();
        this.inventory.clear();
        this.initItems(player);
        itemsMap.forEach((item, uiAction) -> this.inventory.setItem(item.getOriginSlot().getSlot(), item.getItemStack()));

        for (int i = 0; i < this.rows.getSlots(); i++) {

            ItemStack item = inventory.getItem(i);

            if (background.type() == UIBackground.BackgroundType.NONE)
                continue;

            if (item != null && item.getType() != Material.AIR)
                continue;


            switch (background.type()) {
                case FULL -> {

                    UIItem uiItem = background.backgroundItems().get(0);
                    ItemStack itemStack = uiItem.getItemStack();

                    item(new UIItem(itemStack, UISlot.fromSlotId(i).orElse(UISlot.SLOT_0)), (clicker, clickedItem, action, event) -> true);

                    inventory.setItem(i, itemStack);

                }
                case SELF -> {
                    if (background.backgroundItems().size() <= i)
                        return;

                    UIItem uiItem = background.backgroundItems().get(0);
                    item(new UIItem(uiItem.getItemStack(), UISlot.fromSlotId(i).orElse(UISlot.SLOT_0)), (clicker, clickedItem, action, event) -> true);
                    inventory.setItem(i, uiItem.getItemStack());
                }
                case BOTTOM_LINE -> {

                    UIItem uiItem = background.backgroundItems().get(0);

                    if (i >= this.rows.getSlots() - 9) {
                        item(new UIItem(uiItem.getItemStack(), UISlot.fromSlotId(i).orElse(UISlot.SLOT_0)), (clicker, clickedItem, action, event) -> true);
                        inventory.setItem(i, uiItem.getItemStack());
                    }


                }

                case LINE -> {

                    for (UIItem uiItem : this.background.backgroundItems()) {

                        item(uiItem, (clicker, clickedItem, action, event) -> true);
                        inventory.setItem(uiItem.getOriginSlot().getSlot(), uiItem.getItemStack());

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
