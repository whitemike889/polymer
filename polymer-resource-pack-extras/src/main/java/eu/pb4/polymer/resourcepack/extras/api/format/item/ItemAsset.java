package eu.pb4.polymer.resourcepack.extras.api.format.item;

import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import eu.pb4.polymer.resourcepack.extras.api.format.item.model.ItemModel;

public record ItemAsset(ItemModel model, Properties properties) {
    public static final Codec<ItemAsset> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemModel.CODEC.fieldOf("model").forGetter(ItemAsset::model),
            Properties.CODEC.forGetter(ItemAsset::properties)
        ).apply(instance, ItemAsset::new)
    );

    public String toJson() {
        return CODEC.encodeStart(JsonOps.INSTANCE, this).getOrThrow().toString();
    }

    public static ItemAsset fromJson(String json) {
        return CODEC.decode(JsonOps.INSTANCE, JsonParser.parseString(json)).getOrThrow().getFirst();
    }

    public record Properties(boolean handAnimationOnSwap) {
        public static final Properties DEFAULT = new Properties(true);
        public static final MapCodec<Properties> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.BOOL.optionalFieldOf("hand_animation_on_swap", true).forGetter(Properties::handAnimationOnSwap)
        ).apply(instance, Properties::new));
    }
}