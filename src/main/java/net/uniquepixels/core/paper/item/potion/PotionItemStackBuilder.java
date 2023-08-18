package net.uniquepixels.core.paper.item.potion;

import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionItemStackBuilder extends DefaultItemStackBuilder<PotionMeta> {
    public PotionItemStackBuilder(Material material) {
        super(material);
    }

    public PotionItemStackBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public PotionItemStackBuilder setPotionData(PotionData data) {
        getItemMeta().setBasePotionData(data);
        return this;
    }

    public PotionItemStackBuilder addEffect(PotionEffect effect) {
        getItemMeta().addCustomEffect(effect, true);
        return this;
    }

    public PotionItemStackBuilder removeEffect(PotionEffectType effect) {
        getItemMeta().removeCustomEffect(effect);
        return this;
    }

    public PotionItemStackBuilder clearEffects() {
        getItemMeta().clearCustomEffects();
        return this;
    }

    public PotionItemStackBuilder setColor(Color color) {
        getItemMeta().setColor(color);
        return this;
    }
}
