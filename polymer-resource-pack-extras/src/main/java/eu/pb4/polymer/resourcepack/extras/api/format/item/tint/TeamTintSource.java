package eu.pb4.polymer.resourcepack.extras.api.format.item.tint;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;

public record TeamTintSource(int defaultColor) implements ItemTintSource {
    public static final MapCodec<TeamTintSource> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codecs.RGB.fieldOf("default").forGetter(TeamTintSource::defaultColor)
    ).apply(instance, TeamTintSource::new));

    @Override
    public MapCodec<TeamTintSource> codec() {
        return CODEC;
    }
}
