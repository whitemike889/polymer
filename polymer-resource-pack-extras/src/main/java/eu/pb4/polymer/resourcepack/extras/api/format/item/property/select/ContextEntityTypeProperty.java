package eu.pb4.polymer.resourcepack.extras.api.format.item.property.select;

import com.mojang.serialization.MapCodec;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public record ContextEntityTypeProperty() implements SelectProperty<RegistryKey<EntityType<?>>> {
	public static final Type<ContextEntityTypeProperty, RegistryKey<EntityType<?>>> TYPE = new Type<>(
		MapCodec.unit(new ContextEntityTypeProperty()), RegistryKey.createCodec(RegistryKeys.ENTITY_TYPE)
	);

	@Override
	public Type<ContextEntityTypeProperty, RegistryKey<EntityType<?>>> type() {
		return TYPE;
	}
}
