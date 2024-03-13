package net.uniquepixels.core.paper.gui.background;


import net.kyori.adventure.text.Component;
import net.uniquepixels.core.paper.gui.UISlot;
import net.uniquepixels.core.paper.gui.item.UIItem;
import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.util.List;

public record UIBackground(BackgroundType type, List<UIItem> backgroundItems) {

    public static UIBackground createDefaultBackground() {
        return new UIBackground(BackgroundType.FULL, List.of(new UIItem(new DefaultItemStackBuilder<>(Material.GRAY_STAINED_GLASS_PANE)
                .displayName(Component.empty())
                .addFlags(ItemFlag.values())
                .buildItem(), UISlot.SLOT_4)));
    }


    public enum BackgroundType {
        FULL,
        NONE,
        SELF,
        BOTTOM_LINE
    }

}
