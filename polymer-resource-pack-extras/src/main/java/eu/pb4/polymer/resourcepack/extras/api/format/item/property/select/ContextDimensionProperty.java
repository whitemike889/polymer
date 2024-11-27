package eu.pb4.polymer.resourcepack.extras.api.format.item.property.select;

import com.mojang.serialization.MapCodec;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;

public record ContextDimensionProperty() implements SelectProperty<RegistryKey<World>> {
	public static final Type<ContextDimensionProperty, RegistryKey<World>> TYPE = new Type<>(
		MapCodec.unit(new ContextDimensionProperty()), RegistryKey.createCodec(RegistryKeys.WORLD)
	);

	@Override
	public Type<ContextDimensionProperty, RegistryKey<World>> type() {
		return TYPE;
	}
}
