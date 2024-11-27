package eu.pb4.polymer.resourcepack.extras.api.format.item.property.bool;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record KeybindDownProperty(String keybind) implements BooleanProperty {
    public static final MapCodec<KeybindDownProperty> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.fieldOf("keybind").forGetter(KeybindDownProperty::keybind)
            ).apply(instance, KeybindDownProperty::new));

    @Override
    public MapCodec<KeybindDownProperty> codec() {
        return CODEC;
    }
}
