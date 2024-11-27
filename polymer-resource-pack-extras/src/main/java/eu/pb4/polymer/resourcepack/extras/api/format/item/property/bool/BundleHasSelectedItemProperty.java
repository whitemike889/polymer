package eu.pb4.polymer.resourcepack.extras.api.format.item.property.bool;

import com.mojang.serialization.MapCodec;

public record BundleHasSelectedItemProperty() implements BooleanProperty {
    public static final MapCodec<BundleHasSelectedItemProperty> CODEC = MapCodec.unit(new BundleHasSelectedItemProperty());

    @Override
    public MapCodec<BundleHasSelectedItemProperty> codec() {
        return CODEC;
    }
}
