package eu.pb4.polymer.resourcepack.extras.api.format.item.property.select;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record ItemBlockStateProperty(String property) implements SelectProperty<String> {
	public static final Type<ItemBlockStateProperty, String> TYPE = new Type<>(
		RecordCodecBuilder.mapCodec(instance -> instance.group(
				Codec.STRING.fieldOf("block_state_property").forGetter(ItemBlockStateProperty::property)
						)
				.apply(instance, ItemBlockStateProperty::new)
		),
		Codec.STRING
	);

	@Override
	public Type<ItemBlockStateProperty, String> type() {
		return TYPE;
	}
}
