package eu.pb4.polymer.resourcepack.extras.api.format.item.special;

import com.mojang.serialization.MapCodec;

public record DecoratedPotSpecialModel() implements SpecialModel {
    public static final MapCodec<DecoratedPotSpecialModel> CODEC = MapCodec.unit(new DecoratedPotSpecialModel());

    @Override
    public MapCodec<? extends SpecialModel> codec() {
        return CODEC;
    }
}
