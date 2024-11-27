package eu.pb4.polymer.resourcepack.extras.api.format.item.tint;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;

public record PotionTintSource(int defaultColor) implements ItemTintSource {
    public static final MapCodec<PotionTintSource> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codecs.RGB.fieldOf("default").forGetter(PotionTintSource::defaultColor)
    ).apply(instance, PotionTintSource::new));

    public PotionTintSource() {
        this(-13083194);
    }

    @Override
    public MapCodec<PotionTintSource> codec() {
        return CODEC;
    }
}
