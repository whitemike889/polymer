package eu.pb4.polymer.resourcepack.extras.api.format.item.tint;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.type.MapColorComponent;
import net.minecraft.util.dynamic.Codecs;

public record MapColorTintSource(int defaultColor) implements ItemTintSource {
    public static final MapCodec<MapColorTintSource> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codecs.RGB.fieldOf("default").forGetter(MapColorTintSource::defaultColor)
    ).apply(instance, MapColorTintSource::new));

    public MapColorTintSource() {
        this(MapColorComponent.DEFAULT.rgb());
    }

    @Override
    public MapCodec<MapColorTintSource> codec() {
        return CODEC;
    }
}
