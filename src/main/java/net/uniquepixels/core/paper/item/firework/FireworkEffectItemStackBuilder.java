package net.uniquepixels.core.paper.item.firework;

import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.meta.FireworkEffectMeta;

public class FireworkEffectItemStackBuilder extends DefaultItemStackBuilder<FireworkEffectMeta> {
    public FireworkEffectItemStackBuilder() {
        super(Material.FIREWORK_STAR);
    }

    public FireworkEffectItemStackBuilder setEffect(FireworkEffect effect) {
        getItemMeta().setEffect(effect);
        return this;
    }
}
