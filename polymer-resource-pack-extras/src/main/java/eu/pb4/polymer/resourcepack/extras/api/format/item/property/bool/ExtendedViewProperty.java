package eu.pb4.polymer.resourcepack.extras.api.format.item.property.bool;

import com.mojang.serialization.MapCodec;

public record ExtendedViewProperty() implements BooleanProperty {
    public static final MapCodec<ExtendedViewProperty> CODEC = MapCodec.unit(new ExtendedViewProperty());


    @Override
    public MapCodec<ExtendedViewProperty> codec() {
        return CODEC;
    }
}
