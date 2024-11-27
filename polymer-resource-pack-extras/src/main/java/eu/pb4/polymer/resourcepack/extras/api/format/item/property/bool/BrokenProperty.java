package eu.pb4.polymer.resourcepack.extras.api.format.item.property.bool;

import com.mojang.serialization.MapCodec;

public record BrokenProperty() implements BooleanProperty {
    public static final MapCodec<BrokenProperty> CODEC = MapCodec.unit(new BrokenProperty());


    @Override
    public MapCodec<BrokenProperty> codec() {
        return CODEC;
    }
}
