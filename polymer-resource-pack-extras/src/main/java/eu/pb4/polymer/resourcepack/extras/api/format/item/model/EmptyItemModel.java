package eu.pb4.polymer.resourcepack.extras.api.format.item.model;

import com.mojang.serialization.MapCodec;

public record EmptyItemModel() implements ItemModel {
    public static final EmptyItemModel INSTANCE = new EmptyItemModel();
    public static final MapCodec<EmptyItemModel> CODEC = MapCodec.unit(INSTANCE);
    @Override
    public MapCodec<? extends ItemModel> codec() {
        return CODEC;
    }
}
