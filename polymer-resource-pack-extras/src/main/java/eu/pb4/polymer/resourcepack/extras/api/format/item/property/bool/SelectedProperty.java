package eu.pb4.polymer.resourcepack.extras.api.format.item.property.bool;

import com.mojang.serialization.MapCodec;

public record SelectedProperty() implements BooleanProperty {
    public static final MapCodec<SelectedProperty> CODEC = MapCodec.unit(new SelectedProperty());

    @Override
    public MapCodec<SelectedProperty> codec() {
        return CODEC;
    }
}
