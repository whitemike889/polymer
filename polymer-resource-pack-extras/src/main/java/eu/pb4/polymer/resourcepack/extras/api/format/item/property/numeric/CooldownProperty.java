package eu.pb4.polymer.resourcepack.extras.api.format.item.property.numeric;

import com.mojang.serialization.MapCodec;

public record CooldownProperty() implements NumericProperty {
    public static final MapCodec<CooldownProperty> CODEC = MapCodec.unit(new CooldownProperty());

    @Override
    public MapCodec<CooldownProperty> codec() {
        return CODEC;
    }
}
