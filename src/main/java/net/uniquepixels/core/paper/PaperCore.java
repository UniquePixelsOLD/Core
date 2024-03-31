package net.uniquepixels.core.paper;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.translation.GlobalTranslator;
import net.kyori.adventure.translation.TranslationRegistry;
import net.uniquepixels.core.paper.chat.chatinput.ChatInputManager;
import net.uniquepixels.core.paper.gui.backend.UIHolder;
import net.uniquepixels.core.paper.item.ItemCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;
import java.util.ResourceBundle;

public class PaperCore extends JavaPlugin {

    @Override
    public void onEnable() {

        ResourceBundle bundle = ResourceBundle.getBundle("translation");
        ResourceBundle deBundle = ResourceBundle.getBundle("translation_de");
        TranslationRegistry registry = TranslationRegistry.create(Key.key("unique-core"));
        registry.registerAll(Locale.ENGLISH, bundle, false);
        registry.registerAll(Locale.GERMAN, deBundle, false);

        registry.defaultLocale(Locale.ENGLISH);
        GlobalTranslator.translator().addSource(registry);

        this.getSLF4JLogger().info("""

                  _____                       _____              \s
                 |  __ \\                     / ____|             \s
                 | |__) |_ _ _ __   ___ _ __| |     ___  _ __ ___\s
                 |  ___/ _` | '_ \\ / _ \\ '__| |    / _ \\| '__/ _ \\
                 | |  | (_| | |_) |  __/ |  | |___| (_) | | |  __/
                 |_|   \\__,_| .__/ \\___|_|   \\_____\\___/|_|  \\___|
                            | |                                  \s
                            |_|                                  \s
                """);


        UIHolder uiHolder = new UIHolder();
        ChatInputManager chatInputManager = new ChatInputManager();

        this.getServer().getServicesManager().register(UIHolder.class, uiHolder, this, ServicePriority.Normal);
        this.getServer().getServicesManager().register(ChatInputManager.class, chatInputManager, this, ServicePriority.Normal);

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(uiHolder, this);
        pluginManager.registerEvents(chatInputManager, this);

        getCommand("item").setExecutor(new ItemCommand());


    }

}
