package eu.pb4.polymer.resourcepack.extras.api.format.item.property.bool;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.dynamic.Codecs;

import java.util.function.Function;

public interface BooleanProperty {
	Codecs.IdMapper<Identifier, MapCodec<? extends BooleanProperty>> TYPES = Util.make(new Codecs.IdMapper<>(), m -> {
		m.put(Identifier.of("custom_model_data"), CustomModelDataFlagProperty.CODEC);
		m.put(Identifier.of("using_item"), UsingItemProperty.CODEC);
		m.put(Identifier.of("broken"), BrokenProperty.CODEC);
		m.put(Identifier.of("damaged"), DamagedProperty.CODEC);
		m.put(Identifier.of("fishing_rod/cast"), FishingRodCastProperty.CODEC);
		m.put(Identifier.of("has_component"), HasComponentProperty.CODEC);
		m.put(Identifier.of("bundle/has_selected_item"), BundleHasSelectedItemProperty.CODEC);
		m.put(Identifier.of("selected"), SelectedProperty.CODEC);
		m.put(Identifier.of("carried"), CarriedProperty.CODEC);
		m.put(Identifier.of("extended_view"), ExtendedViewProperty.CODEC);
		m.put(Identifier.of("keybind_down"), KeybindDownProperty.CODEC);
		m.put(Identifier.of("view_entity"), ViewEntityProperty.CODEC);
	});
	MapCodec<BooleanProperty> CODEC = TYPES.getCodec(Identifier.CODEC).dispatchMap("property", BooleanProperty::codec, Function.identity());

	MapCodec<? extends BooleanProperty> codec();
}
