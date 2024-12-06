package eu.pb4.polymer.resourcepack.extras.api.format.item.model;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import eu.pb4.polymer.resourcepack.extras.api.format.item.property.select.SelectProperty;
import net.minecraft.util.dynamic.Codecs;

import java.util.List;
import java.util.Optional;

public record SelectItemModel<T extends SelectProperty<V>, V>(Switch<T, V> switchValue,
                                                              Optional<ItemModel> fallback) implements ItemModel {
    public static final MapCodec<SelectItemModel<?, ?>> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Switch.CODEC.forGetter(SelectItemModel::switchValue),
                    ItemModel.CODEC.optionalFieldOf("fallback").forGetter(SelectItemModel::fallback)
            ).apply(instance, SelectItemModel::new)
    );

    @Override
    public MapCodec<SelectItemModel<?, ?>> codec() {
        return CODEC;
    }

    public record Switch<T extends SelectProperty<V>, V>(T property, List<Case<V>> cases) {
        public static final MapCodec<Switch<?, ?>> CODEC = SelectProperty.CODEC
                .dispatchMap("property", unbakedSwitch -> unbakedSwitch.property().type(), SelectProperty.Type::switchCodec);
    }

    public record Case<T>(List<T> values, ItemModel model) {
        public static <T> Codec<Case<T>> createCodec(Codec<T> valueCodec) {
            return RecordCodecBuilder.create(instance -> instance.group(
                            Codecs.nonEmptyList(Codecs.listOrSingle(valueCodec)).fieldOf("when").forGetter(Case::values),
                            ItemModel.CODEC.fieldOf("model").forGetter(Case::model)
                    ).apply(instance, Case::new)
            );
        }
    }
}