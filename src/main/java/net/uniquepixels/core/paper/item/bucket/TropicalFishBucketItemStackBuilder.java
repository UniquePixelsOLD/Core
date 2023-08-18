package net.uniquepixels.core.paper.item.bucket;

import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.TropicalFish;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.TropicalFishBucketMeta;

public class TropicalFishBucketItemStackBuilder extends DefaultItemStackBuilder<TropicalFishBucketMeta> {
    public TropicalFishBucketItemStackBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public TropicalFishBucketItemStackBuilder(Material material) {
        super(material);
    }

    public TropicalFishBucketItemStackBuilder setPatternColor(DyeColor color) {
        getItemMeta().setPatternColor(color);
        return this;
    }

    public TropicalFishBucketItemStackBuilder setBodyColor(DyeColor color) {
        getItemMeta().setBodyColor(color);
        return this;
    }

    public TropicalFishBucketItemStackBuilder setPattern(TropicalFish.Pattern pattern) {
        getItemMeta().setPattern(pattern);
        return this;
    }

}
