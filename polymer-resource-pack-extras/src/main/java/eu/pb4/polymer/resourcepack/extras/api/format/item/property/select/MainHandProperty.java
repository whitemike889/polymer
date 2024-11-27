package eu.pb4.polymer.resourcepack.extras.api.format.item.property.select;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.Arm;

public record MainHandProperty() implements SelectProperty<Arm> {
    public static final Type<MainHandProperty, Arm> TYPE = new Type<>(MapCodec.unit(new MainHandProperty()), Arm.CODEC);

    @Override
    public Type<MainHandProperty, Arm> type() {
        return TYPE;
    }
}
