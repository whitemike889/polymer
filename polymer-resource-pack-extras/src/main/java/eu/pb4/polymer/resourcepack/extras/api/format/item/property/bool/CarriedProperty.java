package eu.pb4.polymer.resourcepack.extras.api.format.item.property.bool;

import com.mojang.serialization.MapCodec;

public record CarriedProperty() implements BooleanProperty {
    public static final MapCodec<CarriedProperty> CODEC = MapCodec.unit(new CarriedProperty());

    @Override
    public MapCodec<CarriedProperty> codec() {
        return CODEC;
    }
}
