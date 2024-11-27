package eu.pb4.polymer.resourcepack.extras.api.format.item.property.select;

import com.mojang.serialization.MapCodec;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public record TrimMaterialProperty() implements SelectProperty<RegistryKey<ArmorTrimMaterial>> {
    public static final Type<TrimMaterialProperty, RegistryKey<ArmorTrimMaterial>> TYPE = new Type<>(
            MapCodec.unit(new TrimMaterialProperty()), RegistryKey.createCodec(RegistryKeys.TRIM_MATERIAL)
    );

    @Override
    public Type<TrimMaterialProperty, RegistryKey<ArmorTrimMaterial>> type() {
        return TYPE;
    }
}
