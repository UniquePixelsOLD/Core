package net.uniquepixels.core.paper.gui.exception;


import net.uniquepixels.core.paper.gui.item.UIItem;

public class AnimatedUpdateException extends Throwable {
    public AnimatedUpdateException(UIItem item) {
        super("Item from origin slot " + item.getCurrentSlot().getSlot() + " can't be animated!");
    }
}
