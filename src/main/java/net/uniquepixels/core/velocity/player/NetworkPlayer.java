package net.uniquepixels.core.velocity.player;

import org.apache.commons.lang.LocaleUtils;

import java.util.Locale;
import java.util.UUID;

public class NetworkPlayer {

    private final UUID id;
    private String locale;
    private boolean online;

    public NetworkPlayer(UUID id, Locale locale, boolean online) {
        this.id = id;
        this.locale = locale.getLanguage();
        this.online = online;
    }

    public UUID getId() {
        return id;
    }

    public Locale getLocale() {
        return LocaleUtils.toLocale(this.locale);
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
