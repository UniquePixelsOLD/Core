package net.uniquepixels.core.velocity.resourcepack.management;


import lombok.Getter;

@Getter
public enum ResourcePackVersion {

    V1_19(760, "1.19"),
    V1_19_3(761, "1.19.3"),
    V1_19_4(762, "1.19.4"),
    V1_20(763, "1.20");

    private final int protocolVersion;
    private final String version;

    ResourcePackVersion(int protocolVersion, String version) {
        this.protocolVersion = protocolVersion;
        this.version = version;
    }

    public static ResourcePackVersion getByProtocolVersion(int protocolVersion) {
        for (ResourcePackVersion version : values()) {
            if (version.protocolVersion == protocolVersion) {
                return version;
            }
        }
        return null;
    }
}
