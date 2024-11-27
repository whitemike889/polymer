package eu.pb4.polymer.resourcepack.extras.api.format.item.special;

import com.mojang.serialization.MapCodec;

public record TridentSpecialModel() implements SpecialModel {
    public static final MapCodec<TridentSpecialModel> CODEC = MapCodec.unit(new TridentSpecialModel());

    @Override
    public MapCodec<? extends SpecialModel> codec() {
        return CODEC;
    }
}
