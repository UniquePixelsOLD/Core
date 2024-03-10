package net.uniquepixels.core.paper.item;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public interface ItemStackBuilder<I extends ItemStackBuilder<I, M>, M extends ItemMeta> {

    ItemStack buildItem();

    I displayName(Component text);

    I addLoreLine(Component text);
    I setLoreLine(int position, Component text);

    I removeLoreLine(int line);

    I removeLoreLines();

    I setCustomMeta(ItemMeta meta);

    @Deprecated
    I applyItemMeta();

    I addEnchantment(Enchantment enchantment, int level);

    I addFlags(ItemFlag... flags);

    I removeFlags(ItemFlag... flags);

    I setAmount(int amount);

    I setUnbreakable(boolean unbreakable);

    I setCustomModelData(int modelData);

    M getItemMeta();

    <K, V> I addData(NamespacedKey namespacedKey, PersistentDataType<K, V> type, V data);

    boolean hasData(NamespacedKey namespacedKey);

    <K,V> V getData(NamespacedKey namespacedKey, PersistentDataType<K,V> type);

}
