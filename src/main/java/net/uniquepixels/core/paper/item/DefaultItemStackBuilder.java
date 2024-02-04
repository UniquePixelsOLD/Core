package net.uniquepixels.core.paper.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultItemStackBuilder<M extends ItemMeta> implements ItemStackBuilder<DefaultItemStackBuilder<M>, M> {

    protected final ItemStack itemStack;
    protected final M meta;

    public DefaultItemStackBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.meta = ((M) itemStack.getItemMeta());
    }

    public DefaultItemStackBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.meta = ((M) itemStack.getItemMeta());
    }


    @Override
    public ItemStack buildItem() {
        return itemStack;
    }

    @Override
    public DefaultItemStackBuilder<M> displayName(Component text) {

        this.meta.displayName(text);

        return this;
    }

    @Override
    public DefaultItemStackBuilder<M> addLoreLine(Component text) {

        ArrayList<Component> lore = new ArrayList<>();

        if (this.meta.lore() != null)
            lore = new ArrayList<>(Objects.requireNonNull(this.meta.lore()));

        lore.add(text);
        this.meta.lore(lore);

        return this;
    }

    @Override
    public DefaultItemStackBuilder<M> setLoreLine(int position, Component text) {

        ArrayList<Component> lore = new ArrayList<>();

        if (this.meta.lore() != null)
            lore = new ArrayList<>(Objects.requireNonNull(this.meta.lore()));

        lore.set(position, text);
        this.meta.lore(lore);

        return this;
    }

    @Override
    public DefaultItemStackBuilder<M> removeLoreLine(int line) {

        ArrayList<Component> lore = new ArrayList<>();

        if (this.meta.lore() != null)
            lore = new ArrayList<>(Objects.requireNonNull(this.meta.lore()));

        if (lore.size() - 1 >= line)
            lore.remove(line);

        this.meta.lore(lore);

        return this;
    }

    @Override
    public DefaultItemStackBuilder<M> removeLoreLines() {
        this.meta.lore(List.of());
        return this;
    }

    @Override
    public DefaultItemStackBuilder<M> setCustomMeta(ItemMeta meta) {
        this.itemStack.setItemMeta(meta);
        return this;
    }

    @Override
    public DefaultItemStackBuilder<M> applyItemMeta() {
        this.itemStack.setItemMeta(meta);
        return this;
    }

    @Override
    public DefaultItemStackBuilder<M> addEnchantment(Enchantment enchantment, int level) {
        this.meta.addEnchant(enchantment, level, true);
        return this;
    }

    @Override
    public DefaultItemStackBuilder<M> addFlags(ItemFlag... flags) {
        this.meta.addItemFlags(flags);
        return this;
    }

    @Override
    public DefaultItemStackBuilder<M> removeFlags(ItemFlag... flags) {
        this.meta.removeItemFlags(flags);
        return this;
    }

    @Override
    public DefaultItemStackBuilder<M> setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    @Override
    public DefaultItemStackBuilder<M> setUnbreakable(boolean unbreakable) {
        this.meta.setUnbreakable(unbreakable);
        return this;
    }

    @Override
    public M getItemMeta() {
        return this.meta;
    }

    @Override
    public <K, V> DefaultItemStackBuilder<M> addData(NamespacedKey namespacedKey, PersistentDataType<K, V> type, V data) {
        this.meta.getPersistentDataContainer().set(namespacedKey, type, data);
        return this;
    }

    @Override
    public boolean hasData(NamespacedKey namespacedKey) {
        return this.meta.getPersistentDataContainer().has(namespacedKey);
    }

    @Override
    public <K, V> V getData(NamespacedKey namespacedKey, PersistentDataType<K, V> type) {
        return this.meta.getPersistentDataContainer().get(namespacedKey, type);
    }
}
