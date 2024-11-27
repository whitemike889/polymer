package eu.pb4.polymer.resourcepack.extras.api.format.item.tint;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;

public record DyeTintSource(int defaultColor) implements ItemTintSource {
    public static final MapCodec<DyeTintSource> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codecs.RGB.fieldOf("default").forGetter(DyeTintSource::defaultColor)
    ).apply(instance, DyeTintSource::new));

    @Override
    public MapCodec<DyeTintSource> codec() {
        return CODEC;
    }
}
