package eu.pb4.polymer.resourcepack.extras.api.format.item.property.bool;

import com.mojang.serialization.MapCodec;

public record ViewEntityProperty() implements BooleanProperty {
    public static final MapCodec<ViewEntityProperty> CODEC = MapCodec.unit(new ViewEntityProperty());

    @Override
    public MapCodec<ViewEntityProperty> codec() {
        return CODEC;
    }
}
