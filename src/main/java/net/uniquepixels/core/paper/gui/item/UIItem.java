package net.uniquepixels.core.paper.gui.item;

import net.kyori.adventure.text.Component;
import net.uniquepixels.core.paper.gui.UISlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;


public class UIItem {

    private final ItemStack itemStack;
    private final UISlot originSlot;
    private UISlot currentSlot;
    private Component displayText;
    private List<Component> displayLore;

    public UIItem(ItemStack itemStack, UISlot originSlot) {
        this.itemStack = itemStack;
        this.originSlot = originSlot;
        this.currentSlot = originSlot;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public UISlot getOriginSlot() {
        return originSlot;
    }

    public UISlot getCurrentSlot() {
        return currentSlot;
    }

    public void setCurrentSlot(UISlot currentSlot) {
        this.currentSlot = currentSlot;
    }

    public Component getDisplayText() {
        return displayText;
    }

    public void setDisplayText(Component displayText) {
        this.displayText = displayText;
    }

    public List<Component> getDisplayLore() {
        return displayLore;
    }

    public void setDisplayLore(List<Component> displayLore) {
        this.displayLore = displayLore;
    }

    public ItemStack buildItem() {

        if (itemStack == null)
            throw new IllegalStateException("item stack can't be nul!");

        ItemMeta meta = itemStack.getItemMeta();
        meta.displayName(displayText);
        meta.lore(displayLore);

        itemStack.setItemMeta(meta);

        return itemStack;
    }


}
