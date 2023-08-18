package net.uniquepixels.core.paper.maps;

import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URI;

public class ImageToMapRenderer extends MapRenderer {

    private final URI imageUri;
    private final BufferedImage image;

    public ImageToMapRenderer(URI imageUri) {
        this.imageUri = imageUri;
        this.image = render();
    }

    @SneakyThrows
    private BufferedImage render() {
        return MapPalette.resizeImage(ImageIO.read(this.imageUri.toURL()));
    }


    @Override
    public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player) {
        canvas.drawImage(0, 0, image);
        map.setTrackingPosition(false);
    }
}
