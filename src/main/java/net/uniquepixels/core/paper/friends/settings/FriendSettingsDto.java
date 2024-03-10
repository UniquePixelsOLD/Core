package net.uniquepixels.core.paper.friends.settings;

import net.uniquepixels.core.paper.friends.FriendDto;

import java.util.UUID;

public record FriendSettingsDto(UUID playerId, AllowFriendRequests allowRequests, boolean notifyInChat, boolean allowJump) {

    public PlainFriendSettingsDto toPlain() {
        return new PlainFriendSettingsDto(playerId.toString(), allowRequests.name().toLowerCase(), notifyInChat, allowJump);
    }

    public record PlainFriendSettingsDto(String playerId, String allowRequests, boolean notifyInChat, boolean allowJumping) {
        public FriendSettingsDto toNormal() {
            return new FriendSettingsDto(UUID.fromString(playerId),AllowFriendRequests.valueOf(allowRequests), notifyInChat, allowJumping);
        }
    }

}
