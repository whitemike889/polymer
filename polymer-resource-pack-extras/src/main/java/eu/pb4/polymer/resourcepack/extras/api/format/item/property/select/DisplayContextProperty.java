package eu.pb4.polymer.resourcepack.extras.api.format.item.property.select;

import com.mojang.serialization.MapCodec;
import net.minecraft.item.ModelTransformationMode;

public record DisplayContextProperty() implements SelectProperty<ModelTransformationMode> {
    public static final Type<DisplayContextProperty, ModelTransformationMode> TYPE = new Type<>(
            MapCodec.unit(new DisplayContextProperty()), ModelTransformationMode.CODEC
    );

    @Override
    public Type<DisplayContextProperty, ModelTransformationMode> type() {
        return TYPE;
    }
}
