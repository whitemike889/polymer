package eu.pb4.polymer.resourcepack.extras.api.format.item.property.numeric;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record CountProperty(boolean normalize) implements NumericProperty {
    public static final MapCodec<CountProperty> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.BOOL.optionalFieldOf("normalize", true).forGetter(CountProperty::normalize)
            ).apply(instance, CountProperty::new)
    );

    @Override
    public MapCodec<CountProperty> codec() {
        return CODEC;
    }
}
