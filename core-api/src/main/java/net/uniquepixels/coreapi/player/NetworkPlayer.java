package net.uniquepixels.coreapi.player;

import java.util.Locale;
import java.util.UUID;

public class NetworkPlayer {

    private final UUID id;
    private String locale;
    private boolean online;
    private String server;

    public NetworkPlayer(UUID id, Locale locale, boolean online) {
        this.id = id;
        this.locale = locale.getLanguage();
        this.online = online;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public UUID getId() {
        return id;
    }

    public Locale getLocale() {
        return Locale.forLanguageTag(this.locale);
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale.getVariant();
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

}
