package net.uniquepixels.core.paper.item.armor;

import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LeatherItemStackBuilder extends DefaultItemStackBuilder<LeatherArmorMeta> {

    public LeatherItemStackBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public LeatherItemStackBuilder(Material material) {
        super(material);
    }

    public LeatherItemStackBuilder setColor(Color color) {
        getItemMeta().setColor(color);
        return this;
    }
}
