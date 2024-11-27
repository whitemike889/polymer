package eu.pb4.polymer.resourcepack.extras.api.format.item.property.bool;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;

public record HasComponentProperty(ComponentType<?> componentType, boolean ignoreDefault) implements BooleanProperty {
    public static final MapCodec<HasComponentProperty> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Registries.DATA_COMPONENT_TYPE.getCodec().fieldOf("component").forGetter(HasComponentProperty::componentType),
                    Codec.BOOL.optionalFieldOf("ignore_default", Boolean.valueOf(false)).forGetter(HasComponentProperty::ignoreDefault)
            ).apply(instance, HasComponentProperty::new)
    );

    @Override
    public MapCodec<HasComponentProperty> codec() {
        return CODEC;
    }
}
