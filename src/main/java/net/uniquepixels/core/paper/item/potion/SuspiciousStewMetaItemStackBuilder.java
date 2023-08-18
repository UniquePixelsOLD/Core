package net.uniquepixels.core.paper.item.potion;

import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SuspiciousStewMetaItemStackBuilder extends DefaultItemStackBuilder<SuspiciousStewMeta> {
    public SuspiciousStewMetaItemStackBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public SuspiciousStewMetaItemStackBuilder(Material material) {
        super(material);
    }

    public SuspiciousStewMetaItemStackBuilder addEffect(PotionEffect effect) {
        getItemMeta().addCustomEffect(effect, true);
        return this;
    }

    public SuspiciousStewMetaItemStackBuilder removeEffect(PotionEffectType effect) {
        getItemMeta().removeCustomEffect(effect);
        return this;
    }
}
