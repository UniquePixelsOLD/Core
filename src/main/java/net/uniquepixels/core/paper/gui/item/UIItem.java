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



}
