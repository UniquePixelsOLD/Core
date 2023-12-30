package net.uniquepixels.core.paper;

import net.uniquepixels.core.paper.gui.backend.UIHolder;
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

        this.getServer().getServicesManager().register(UIHolder.class, uiHolder, this, ServicePriority.Normal);

    }

}
