package eu.pb4.polymer.resourcepack.extras.api.format.item.property.bool;

import com.mojang.serialization.MapCodec;

public record FishingRodCastProperty() implements BooleanProperty {
    public static final MapCodec<FishingRodCastProperty> CODEC = MapCodec.unit(new FishingRodCastProperty());


    @Override
    public MapCodec<FishingRodCastProperty> codec() {
        return CODEC;
    }
}
