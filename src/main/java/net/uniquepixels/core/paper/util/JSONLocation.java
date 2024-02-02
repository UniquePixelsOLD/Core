package net.uniquepixels.core.paper.util;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

public record JSONLocation(Location location) {

    public static JSONLocation fromString(String location) {
        JsonObject json = JsonParser.parseString(location).getAsJsonObject();

        String world = json.get("world").getAsString();
        double x = json.get("x").getAsDouble();
        double y = json.get("y").getAsDouble();
        double z = json.get("z").getAsDouble();
        float yaw = json.get("yaw").getAsFloat();
        float pitch = json.get("pitch").getAsFloat();

        World bukkitWorld = Bukkit.getWorld(UUID.fromString(world));
        if (bukkitWorld == null)
            return null;

        return new JSONLocation(new Location(bukkitWorld, x, y, z, yaw, pitch));
    }

    @Override
    public String toString() {

        JsonObject object = new JsonObject();
        object.addProperty("world", location.getWorld().getUID().toString());
        object.addProperty("x", location.getX());
        object.addProperty("y", location.getY());
        object.addProperty("z", location.getZ());
        object.addProperty("yaw", location.getYaw());
        object.addProperty("pitch", location.getPitch());

        return object.toString();
    }
}
