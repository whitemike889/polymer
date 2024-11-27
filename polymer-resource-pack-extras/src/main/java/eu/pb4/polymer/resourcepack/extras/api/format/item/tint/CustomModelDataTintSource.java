package eu.pb4.polymer.resourcepack.extras.api.format.item.tint;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;

public record CustomModelDataTintSource(int index, int defaultColor) implements ItemTintSource {
    public static final MapCodec<CustomModelDataTintSource> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codecs.NON_NEGATIVE_INT.optionalFieldOf("index", 0).forGetter(CustomModelDataTintSource::index),
            Codecs.RGB.fieldOf("default").forGetter(CustomModelDataTintSource::defaultColor)
    ).apply(instance, CustomModelDataTintSource::new));

    @Override
    public MapCodec<CustomModelDataTintSource> codec() {
        return CODEC;
    }
}
