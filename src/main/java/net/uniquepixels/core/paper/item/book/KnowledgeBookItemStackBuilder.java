package net.uniquepixels.core.paper.item.book;

import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.KnowledgeBookMeta;

import java.util.List;

public class KnowledgeBookItemStackBuilder extends DefaultItemStackBuilder<KnowledgeBookMeta> {
    public KnowledgeBookItemStackBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public KnowledgeBookItemStackBuilder(Material material) {
        super(material);
    }

    public KnowledgeBookItemStackBuilder setRecipes(List<NamespacedKey> recipes) {
        getItemMeta().setRecipes(recipes);
        return this;
    }

    public KnowledgeBookItemStackBuilder addRecipe(NamespacedKey... recipes) {
        getItemMeta().addRecipe(recipes);
        return this;
    }

}
