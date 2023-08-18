package net.uniquepixels.core.paper.item.egg;

import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SpawnEggMeta;

public class SpawnEggItemStackBuilder extends DefaultItemStackBuilder<SpawnEggMeta> {
    public SpawnEggItemStackBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public SpawnEggItemStackBuilder(Material material) {
        super(material);
    }

    public SpawnEggItemStackBuilder setCustomSpawnedType(EntityType type) {
        getItemMeta().setCustomSpawnedType(type);
        return this;
    }
}
