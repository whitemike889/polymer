package eu.pb4.polymer.resourcepack.extras.api.format.item.property.bool;

import com.mojang.serialization.MapCodec;

public record UsingItemProperty() implements BooleanProperty {
    public static final MapCodec<UsingItemProperty> CODEC = MapCodec.unit(new UsingItemProperty());

    @Override
    public MapCodec<UsingItemProperty> codec() {
        return CODEC;
    }
}
