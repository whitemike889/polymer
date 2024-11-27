package eu.pb4.polymer.resourcepack.extras.api.format.item.model;

import com.mojang.serialization.MapCodec;

public record BundleSelectedItemModel() implements ItemModel {
    public static final BundleSelectedItemModel INSTANCE = new BundleSelectedItemModel();
    public static final MapCodec<BundleSelectedItemModel> CODEC = MapCodec.unit(INSTANCE);
    @Override
    public MapCodec<? extends ItemModel> codec() {
        return CODEC;
    }
}
