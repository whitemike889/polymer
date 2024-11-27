package eu.pb4.polymer.resourcepack.extras.api.format.item.property.numeric;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record DamageProperty(boolean normalize) implements NumericProperty {
    public static final MapCodec<DamageProperty> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.BOOL.optionalFieldOf("normalize", true).forGetter(DamageProperty::normalize)
            ).apply(instance, DamageProperty::new)
    );

    @Override
    public MapCodec<DamageProperty> codec() {
        return CODEC;
    }
}
