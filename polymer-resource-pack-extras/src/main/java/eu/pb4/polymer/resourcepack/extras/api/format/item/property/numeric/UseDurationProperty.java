package eu.pb4.polymer.resourcepack.extras.api.format.item.property.numeric;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record UseDurationProperty(boolean remaining) implements NumericProperty {
    public static final MapCodec<UseDurationProperty> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.BOOL.optionalFieldOf("remaining", false).forGetter(UseDurationProperty::remaining)
            ).apply(instance, UseDurationProperty::new)
    );

    @Override
    public MapCodec<UseDurationProperty> codec() {
        return CODEC;
    }

}
