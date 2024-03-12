package net.uniquepixels.core.paper;

import net.uniquepixels.core.paper.chat.chatinput.ChatInputManager;
import net.uniquepixels.core.paper.gui.backend.UIHolder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class PaperCore extends JavaPlugin {

    @Override
    public void onEnable() {

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


    }

}
