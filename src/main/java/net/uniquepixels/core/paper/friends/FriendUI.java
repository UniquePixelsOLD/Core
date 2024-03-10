package net.uniquepixels.core.paper.friends;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.uniquepixels.core.paper.gui.UIRow;
import net.uniquepixels.core.paper.gui.UISlot;
import net.uniquepixels.core.paper.gui.UIStyle;
import net.uniquepixels.core.paper.gui.backend.UIHolder;
import net.uniquepixels.core.paper.gui.exception.OutOfInventoryException;
import net.uniquepixels.core.paper.gui.item.UIItem;
import net.uniquepixels.core.paper.gui.types.chest.ChestUI;
import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Locale;

public class FriendUI extends ChestUI {
    private final UIHolder uiHolder;

    public FriendUI(UIHolder uiHolder) {
        super(Component.translatable("friend.title").color(NamedTextColor.GRAY), UIRow.CHEST_ROW_3);
        this.uiHolder = uiHolder;
    }

    @Override
    public boolean allowItemMovementInOtherInventories() {
        return false;
    }

    @Override
    protected void initItems(Player opener) throws OutOfInventoryException {

        Locale locale = opener.locale();

        item(new UIItem(new DefaultItemStackBuilder<>(Material.LIME_SHULKER_BOX)
                .displayName(UIStyle.UI_ARROW.append(UIStyle.translate(locale, "friend.friends").color(UIStyle.DEFAULT_COLOR)))
                .addLoreLine(UIStyle.leftClick(locale).append(UIStyle.MINUS)
                        .append(UIStyle.translate(locale, "friend.friends.lore")))
                .buildItem(), UISlot.SLOT_11), (clicker, clickedItem, action, event) -> {



            return true;
        });

    }

    @Override
    public void onClose(Player player) {

    }
}
