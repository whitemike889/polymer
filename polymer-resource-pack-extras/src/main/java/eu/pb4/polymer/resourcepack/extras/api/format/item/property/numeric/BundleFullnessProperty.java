package eu.pb4.polymer.resourcepack.extras.api.format.item.property.numeric;

import com.mojang.serialization.MapCodec;

public record BundleFullnessProperty() implements NumericProperty {
    public static final MapCodec<BundleFullnessProperty> CODEC = MapCodec.unit(new BundleFullnessProperty());

    @Override
    public MapCodec<BundleFullnessProperty> codec() {
        return CODEC;
    }
}
