package net.uniquepixels.core.velocity.resourcepack.management;

import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.velocitypowered.api.proxy.ProxyServer;
import net.uniquepixels.core.velocity.VelocityCore;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ResourcePackHandler {
    private final Map<ResourcePackVersion, byte[]> resourcePackHashCodes = new HashMap<>();

    public ResourcePackHandler(VelocityCore core, ProxyServer proxyServer) {
        proxyServer.getScheduler().buildTask(core, () -> {

            for (ResourcePackVersion resourcePackVersion : ResourcePackVersion.values()) {
                try {
                    resourcePackHashCodes.put(resourcePackVersion, getHashCodeFromUrl(getTexturePackFromVersion(resourcePackVersion)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }).repeat(10, TimeUnit.MINUTES);
    }

    private static void downloadFile(String url, String file) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new URL(url).openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count;
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }

    public Map<ResourcePackVersion, byte[]> getResourcePackHashCodes() {
        return resourcePackHashCodes;
    }

    private byte[] getHashCodeFromUrl(String url) throws IOException {

        Path path = Path.of(System.getProperty("user.home") + "/resource_pack/" + System.currentTimeMillis() + ".zip");

        File dir = new File(System.getProperty("user.home") + "/resource_pack/");
        if (!dir.exists())
            dir.mkdirs();

        File downloadedFile = new File(path.toString());

        downloadedFile.createNewFile();

        downloadFile(url, path.toString());

        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            downloadedFile.delete();
        }, 3, TimeUnit.SECONDS);

        return Files.asByteSource(new File(path.toString())).hash(Hashing.md5()).asBytes();
    }

    public String getTexturePackFromVersion(ResourcePackVersion protocolVersion) {
        return "https://cdn.laudynetwork.com/rp-" + protocolVersion.getVersion() + ".zip";
    }
}
