package eu.pb4.polymer.resourcepack.extras.api.format.item.model;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import eu.pb4.polymer.resourcepack.extras.api.format.item.property.bool.BooleanProperty;


public record ConditionItemModel(BooleanProperty property, ItemModel onTrue, ItemModel onFalse) implements ItemModel {
    public static final MapCodec<ConditionItemModel> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    BooleanProperty.CODEC.forGetter(ConditionItemModel::property),
                    ItemModel.CODEC.fieldOf("on_true").forGetter(ConditionItemModel::onTrue),
                    ItemModel.CODEC.fieldOf("on_false").forGetter(ConditionItemModel::onFalse)
            ).apply(instance, ConditionItemModel::new)
    );

    @Override
    public MapCodec<ConditionItemModel> codec() {
        return CODEC;
    }
}