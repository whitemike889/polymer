package eu.pb4.polymer.resourcepack.api;

import java.nio.charset.StandardCharsets;

public interface WritableAsset {
    byte[] toBytes();

    interface Json extends WritableAsset {
        String toJson();
        @Override
        default byte[] toBytes() {
            return toJson().getBytes(StandardCharsets.UTF_8);
        }
    }
}
