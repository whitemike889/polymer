package eu.pb4.polymer.resourcepack.extras.api.format.atlas;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record DirectoryAtlasSource(String source, String prefix) implements AtlasSource {
    public static final MapCodec<DirectoryAtlasSource> CODEC = RecordCodecBuilder.mapCodec(instance  -> instance.group(
                    Codec.STRING.fieldOf("source").forGetter(DirectoryAtlasSource::source),
                    Codec.STRING.fieldOf("prefix").forGetter(DirectoryAtlasSource::prefix))
            .apply(instance, DirectoryAtlasSource::new));

    @Override
    public MapCodec<? extends AtlasSource> codec() {
        return CODEC;
    }
}
