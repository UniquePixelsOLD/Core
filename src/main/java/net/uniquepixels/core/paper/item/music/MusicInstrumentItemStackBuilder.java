package net.uniquepixels.core.paper.item.music;

import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.MusicInstrument;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MusicInstrumentMeta;

public class MusicInstrumentItemStackBuilder extends DefaultItemStackBuilder<MusicInstrumentMeta> {
    public MusicInstrumentItemStackBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public MusicInstrumentItemStackBuilder(Material material) {
        super(material);
    }

    public MusicInstrumentItemStackBuilder setInstrument(MusicInstrument instrument) {
        getItemMeta().setInstrument(instrument);
        return this;
    }
}
