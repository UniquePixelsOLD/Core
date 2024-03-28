package net.uniquepixels.core.paper.gui.background;


import net.kyori.adventure.text.Component;
import net.uniquepixels.core.paper.gui.UIRow;
import net.uniquepixels.core.paper.gui.UISlot;
import net.uniquepixels.core.paper.gui.item.UIItem;
import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.List;

public record UIBackground(BackgroundType type, List<UIItem> backgroundItems) {

    public static UIBackground createDefaultBackground() {
        return new UIBackground(BackgroundType.FULL, List.of(new UIItem(new DefaultItemStackBuilder<>(Material.GRAY_STAINED_GLASS_PANE)
                .displayName(Component.empty())
                .addFlags(ItemFlag.values())
                .buildItem(), UISlot.SLOT_4)));
    }

    public static UIBackground drawLine(UIRow lineIn) {

        ArrayList<UIItem> items = new ArrayList<>();

        for (UISlot uiSlot : UISlot.getSlotsForRow(lineIn)) {

            items.add(new UIItem(new DefaultItemStackBuilder<>(Material.GRAY_STAINED_GLASS_PANE)
                    .displayName(Component.empty())
                    .addFlags(ItemFlag.values())
                    .buildItem(), uiSlot));

        }

        return new UIBackground(BackgroundType.LINE, items);
    }


    public enum BackgroundType {
        FULL,
        NONE,
        SELF,
        BOTTOM_LINE,
        LINE
    }

}
