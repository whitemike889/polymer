package eu.pb4.polymer.resourcepack.extras.api.format.item.model;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record CompositeItemModel(List<ItemModel> models) implements ItemModel {
    public static final MapCodec<CompositeItemModel> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ItemModel.CODEC.listOf().fieldOf("models").forGetter(CompositeItemModel::models)
    ).apply(instance, CompositeItemModel::new));

    @Override
    public MapCodec<? extends ItemModel> codec() {
        return CODEC;
    }
}
