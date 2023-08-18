package net.uniquepixels.core.paper.item.firework;

import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkItemStackBuilder extends DefaultItemStackBuilder<FireworkMeta> {

    public FireworkItemStackBuilder() {
        super(Material.FIREWORK_ROCKET);
    }

    public FireworkItemStackBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public FireworkItemStackBuilder addEffect(FireworkEffect effect) {
        getItemMeta().addEffect(effect);
        return this;
    }

    public FireworkItemStackBuilder setPower(int power) {
        getItemMeta().setPower(power);
        return this;
    }

    public FireworkItemStackBuilder clearEffects() {
        getItemMeta().clearEffects();
        return this;
    }
}
