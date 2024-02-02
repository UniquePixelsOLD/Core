package net.uniquepixels.core.paper.chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatHead {

    private static final String BASE_URL = "https://crafthead.net/avatar/";

    private String convertUID(UUID uuid) {
        return uuid.toString().replace("-", "");
    }

    public List<Component> getLines(UUID uuid) throws IOException {
        String rawId = convertUID(uuid);

        List<Component> list = new ArrayList<>();

        BufferedImage bufferedImage = ImageIO.read(new URL(BASE_URL + rawId));

        for (int y = 0; y < bufferedImage.getHeight(); y++) {

            var line = Component.empty();

            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                line = line.append(Component.text("⬛").color(TextColor.color(bufferedImage.getRGB(x, y))));
            }

            list.add(line);

        }

        return list;
    }
}
