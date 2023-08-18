package net.uniquepixels.core.paper.item.map;

import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

public class MapItemStackBuilder extends DefaultItemStackBuilder<MapMeta> {
    public MapItemStackBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public MapItemStackBuilder(Material material) {
        super(material);
    }

    public MapItemStackBuilder setView(MapView view) {
        getItemMeta().setMapView(view);
        return this;
    }

    public MapItemStackBuilder setScaling(boolean scale) {
        getItemMeta().setScaling(scale);
        return this;
    }

    public MapItemStackBuilder setColor(Color color) {
        getItemMeta().setColor(color);
        return this;
    }
}
