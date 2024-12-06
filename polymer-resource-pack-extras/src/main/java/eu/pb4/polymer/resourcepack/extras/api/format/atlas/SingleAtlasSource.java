package eu.pb4.polymer.resourcepack.extras.api.format.atlas;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;

import java.util.Optional;

public record SingleAtlasSource(Identifier resource, Optional<Identifier> sprite) implements AtlasSource {
    public static final MapCodec<SingleAtlasSource> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Identifier.CODEC.fieldOf("resource").forGetter(SingleAtlasSource::resource),
            Identifier.CODEC.optionalFieldOf("sprite").forGetter(SingleAtlasSource::sprite)
    ).apply(instance, SingleAtlasSource::new));

    @Override
    public MapCodec<? extends AtlasSource> codec() {
        return CODEC;
    }
}
