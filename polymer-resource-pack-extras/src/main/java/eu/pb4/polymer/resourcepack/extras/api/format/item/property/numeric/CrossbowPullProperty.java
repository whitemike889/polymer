package eu.pb4.polymer.resourcepack.extras.api.format.item.property.numeric;

import com.mojang.serialization.MapCodec;

public class CrossbowPullProperty implements NumericProperty {
	public static final MapCodec<CrossbowPullProperty> CODEC = MapCodec.unit(new CrossbowPullProperty());

	@Override
	public MapCodec<CrossbowPullProperty> codec() {
		return CODEC;
	}
}
