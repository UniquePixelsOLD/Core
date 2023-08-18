package net.uniquepixels.core.paper.item;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LeatherItemStackBuilder extends DefaultItemStackBuilder<LeatherArmorMeta> {

    public LeatherItemStackBuilder(ItemStack itemStack) {
        super(itemStack, ((LeatherArmorMeta) itemStack.getItemMeta()));

        if (!itemStack.getType().name().contains("LEATHER"))
            throw new IllegalCallerException("The provided ItemStack is not a part of a LEATHER_ARMOR!");
    }

    public LeatherItemStackBuilder setColor(Color color) {
        getItemMeta().setColor(color);
        return this;
    }
}
