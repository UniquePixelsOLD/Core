package net.uniquepixels.core.paper.item.bucket;

import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Axolotl;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.AxolotlBucketMeta;

public class AxolotlBucketItemStackBuilder extends DefaultItemStackBuilder<AxolotlBucketMeta> {
    public AxolotlBucketItemStackBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public AxolotlBucketItemStackBuilder(Material material) {
        super(material);
    }

    public AxolotlBucketItemStackBuilder setVariant(Axolotl.Variant variant) {
        getItemMeta().setVariant(variant);
        return this;
    }


}
