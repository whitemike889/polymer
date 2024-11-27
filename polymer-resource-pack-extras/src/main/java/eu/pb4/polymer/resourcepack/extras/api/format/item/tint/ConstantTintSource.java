package eu.pb4.polymer.resourcepack.extras.api.format.item.tint;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;

public record ConstantTintSource(int value) implements ItemTintSource {
    public static final MapCodec<ConstantTintSource> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codecs.RGB.fieldOf("value").forGetter(ConstantTintSource::value)
    ).apply(instance, ConstantTintSource::new));

    @Override
    public MapCodec<ConstantTintSource> codec() {
        return CODEC;
    }
}
