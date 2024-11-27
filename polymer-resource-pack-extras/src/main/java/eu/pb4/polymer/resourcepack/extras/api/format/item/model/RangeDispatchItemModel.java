package eu.pb4.polymer.resourcepack.extras.api.format.item.model;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import eu.pb4.polymer.resourcepack.extras.api.format.item.property.numeric.NumericProperty;

import java.util.List;
import java.util.Optional;

public record RangeDispatchItemModel(NumericProperty property, float scale, List<Entry> entries, Optional<ItemModel> fallback) implements ItemModel {
    public static final MapCodec<RangeDispatchItemModel> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            NumericProperty.CODEC.forGetter(RangeDispatchItemModel::property),
            Codec.FLOAT.optionalFieldOf("scale", 1f).forGetter(RangeDispatchItemModel::scale),
            Entry.CODEC.listOf().fieldOf("entries").forGetter(RangeDispatchItemModel::entries),
            ItemModel.CODEC.optionalFieldOf("fallback").forGetter(RangeDispatchItemModel::fallback)
    ).apply(instance, RangeDispatchItemModel::new));

    @Override
    public MapCodec<? extends ItemModel> codec() {
        return CODEC;
    }

    public record Entry(float threshold, ItemModel model) {
        public static final Codec<Entry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.FLOAT.fieldOf("threshold").forGetter(Entry::threshold),
                ItemModel.CODEC.fieldOf("model").forGetter(Entry::model)
        ).apply(instance, Entry::new));
    }
}
