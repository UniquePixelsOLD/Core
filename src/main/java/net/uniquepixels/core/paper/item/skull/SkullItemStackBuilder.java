package net.uniquepixels.core.paper.item.skull;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.uniquepixels.core.paper.item.DefaultItemStackBuilder;
import net.uniquepixels.core.paper.paper.TextureFetcher;
import net.uniquepixels.core.paper.paper.UUIDFetcher;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

public class SkullItemStackBuilder extends DefaultItemStackBuilder<SkullMeta> {
    public SkullItemStackBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public SkullItemStackBuilder(Material material) {
        super(material);
    }

    public SkullItemStackBuilder setSkullOwner(OfflinePlayer player) {
        getItemMeta().setOwningPlayer(player);
        return this;
    }

    public SkullItemStackBuilder setSkullOwner(@NotNull String url) {

        String finalUrl = "";

        if (url.length() <= 16) {
            UUID uuid = UUIDFetcher.getUUID(url);
            if (uuid == null)
                finalUrl = "https://textures.minecraft.net/texture/" +
                        "647cf0f3b9ec9df2485a9cd4795b60a391c8e6ebac96354de06e3357a9a88607";
            else finalUrl = TextureFetcher.getSkinUrl(uuid.toString());
        } else {
            finalUrl = "https://textures.minecraft.net/texture/" + url;
        }

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "Testuser");
        byte[] data = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", finalUrl).getBytes());
        gameProfile.getProperties().put("textures", new Property("textures", new String(data)));
        try {
            Field field = this.meta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(this.meta, gameProfile);
            field.setAccessible(false);
        } catch (Exception ignored) {
        }
        return this;
    }
}
