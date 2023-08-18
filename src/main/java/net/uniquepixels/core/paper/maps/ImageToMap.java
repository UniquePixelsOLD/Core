package net.uniquepixels.core.paper.maps;

import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;

import java.net.URI;

public record ImageToMap(URI imageUri, World world) {

    public ItemStack getImageMap() {

        val map = Bukkit.createMap(world);

        map.getRenderers().clear();
        map.getRenderers().add(new ImageToMapRenderer(imageUri));

        val itemStack = new ItemStack(Material.FILLED_MAP);
        val meta = (MapMeta) itemStack.getItemMeta();
        assert meta != null;
        meta.setMapView(map);
        meta.setUnbreakable(true);
        itemStack.setItemMeta(meta);

        return itemStack;
    }


}
