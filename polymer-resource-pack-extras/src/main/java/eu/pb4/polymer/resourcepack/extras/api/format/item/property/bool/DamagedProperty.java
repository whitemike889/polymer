package eu.pb4.polymer.resourcepack.extras.api.format.item.property.bool;

import com.mojang.serialization.MapCodec;

public record DamagedProperty() implements BooleanProperty {
    public static final MapCodec<DamagedProperty> CODEC = MapCodec.unit(new DamagedProperty());

    @Override
    public MapCodec<DamagedProperty> codec() {
        return CODEC;
    }
}
