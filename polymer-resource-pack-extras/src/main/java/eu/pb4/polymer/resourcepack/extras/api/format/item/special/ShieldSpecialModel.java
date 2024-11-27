package eu.pb4.polymer.resourcepack.extras.api.format.item.special;

import com.mojang.serialization.MapCodec;

public record ShieldSpecialModel() implements SpecialModel {
    public static final MapCodec<ShieldSpecialModel> CODEC = MapCodec.unit(new ShieldSpecialModel());

    @Override
    public MapCodec<? extends SpecialModel> codec() {
        return CODEC;
    }
}
