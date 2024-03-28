package net.uniquepixels.core.velocity.player;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

public class PlayerManager {

    private final OkHttpClient httpClient = new OkHttpClient();

    private final String requestUrl = System.getenv("BASE_URL") + "/player/";

    public Optional<NetworkPlayer> getNetworkPlayer(UUID uuid) {

        Request request = new Request.Builder()
                .get()
                .url(this.requestUrl + uuid.toString())
                .build();

        try {
            Response execute = this.httpClient.newCall(request).execute();

            NetworkPlayer networkPlayer = new Gson().fromJson(execute.body().string(), NetworkPlayer.class);

            return Optional.of(networkPlayer);

        } catch (IOException e) {
            return Optional.empty();
        }

    }

    public Optional<NetworkPlayer> createNetworkPlayer(UUID uuid, Locale initLocale) {
        Request request = new Request.Builder()
                .post(RequestBody.create(new Gson().toJson(new NetworkPlayer(uuid, initLocale, true)), MediaType.get("application/json")))
                .url(this.requestUrl + "create")
                .build();

        try {
            Response execute = this.httpClient.newCall(request).execute();

            if (execute.code() != 201) {
                return Optional.empty();
            }

            NetworkPlayer networkPlayer = new Gson().fromJson(execute.body().string(), NetworkPlayer.class);

            return Optional.of(networkPlayer);

        } catch (IOException e) {
            return Optional.empty();
        }

    }

    public Optional<NetworkPlayer> changeLocale(UUID uuid, Locale newLocale) {
        Request request = new Request.Builder()
                .put(RequestBody.create("", MediaType.get("application/json")))
                .url(this.requestUrl + "change/locale/" + uuid.toString())
                .header("locale", newLocale.getLanguage())
                .build();

        try {
            Response execute = this.httpClient.newCall(request).execute();

            NetworkPlayer networkPlayer = new Gson().fromJson(execute.body().string(), NetworkPlayer.class);

            return Optional.of(networkPlayer);

        } catch (IOException e) {
            return Optional.empty();
        }

    }

    public Optional<NetworkPlayer> changeOnlineStatus(UUID uuid, boolean online) {
        Request request = new Request.Builder()
                .put(RequestBody.create("", MediaType.get("application/json")))
                .url(this.requestUrl + "change/online/" + uuid.toString())
                .header("online", String.valueOf(online))
                .build();

        try {
            Response execute = this.httpClient.newCall(request).execute();

            NetworkPlayer networkPlayer = new Gson().fromJson(execute.body().string(), NetworkPlayer.class);

            return Optional.of(networkPlayer);

        } catch (IOException e) {
            return Optional.empty();
        }

    }

}
