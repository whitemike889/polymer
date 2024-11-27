package eu.pb4.polymer.resourcepack.extras.api.format.item.special;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;

import java.util.Optional;

public record SignSpecialModel(WoodType woodType, Optional<Identifier> texture) implements SpecialModel {
    public static final MapCodec<SignSpecialModel> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    WoodType.CODEC.fieldOf("wood_type").forGetter(SignSpecialModel::woodType),
                    Identifier.CODEC.optionalFieldOf("texture").forGetter(SignSpecialModel::texture)
            ).apply(instance, SignSpecialModel::new)
    );

    @Override
    public MapCodec<? extends SpecialModel> codec() {
        return CODEC;
    }
}
