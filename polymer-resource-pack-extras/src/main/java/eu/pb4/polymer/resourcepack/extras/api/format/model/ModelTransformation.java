package eu.pb4.polymer.resourcepack.extras.api.format.model;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.Vec3d;

public record ModelTransformation(Vec3d rotation, Vec3d translation, Vec3d scale) {
    public static final Codec<ModelTransformation> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Vec3d.CODEC.optionalFieldOf("rotation", Vec3d.ZERO).forGetter(ModelTransformation::rotation),
            Vec3d.CODEC.optionalFieldOf("translation", Vec3d.ZERO).forGetter(ModelTransformation::translation),
            Vec3d.CODEC.optionalFieldOf("scale", Vec3d.ZERO).forGetter(ModelTransformation::scale)
    ).apply(instance, ModelTransformation::new));
}
