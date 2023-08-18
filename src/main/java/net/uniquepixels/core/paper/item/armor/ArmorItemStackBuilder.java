package net.uniquepixels.core.paper.item.armor;

import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;

public class ArmorItemStackBuilder extends DefaultItemStackBuilder<ArmorMeta> {
    public ArmorItemStackBuilder(Material material) {
        super(material);
    }

    public ArmorItemStackBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public ArmorItemStackBuilder setTrim(ArmorTrim trim) {
        getItemMeta().setTrim(trim);
        return this;
    }
}
