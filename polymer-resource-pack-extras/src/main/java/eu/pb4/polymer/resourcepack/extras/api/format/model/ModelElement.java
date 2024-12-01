package eu.pb4.polymer.resourcepack.extras.api.format.model;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public record ModelElement(Vec3d from, Vec3d to, Map<Direction, Face> faces, Optional<Rotation> rotation,
                           boolean shade, int lightEmission) {
    public static final Codec<ModelElement> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Vec3d.CODEC.fieldOf("from").forGetter(ModelElement::from),
            Vec3d.CODEC.fieldOf("to").forGetter(ModelElement::to),
            Codec.unboundedMap(Direction.CODEC, Face.CODEC).optionalFieldOf("faces", Map.of()).forGetter(ModelElement::faces),
            Rotation.CODEC.optionalFieldOf("rotation").forGetter(ModelElement::rotation),
            Codec.BOOL.optionalFieldOf("shade", true).forGetter(ModelElement::shade),
            Codecs.rangedInt(0, 15).optionalFieldOf("light_emission", 0).forGetter(ModelElement::lightEmission)
    ).apply(instance, ModelElement::new));

    public ModelElement(Vec3d from, Vec3d to, Map<Direction, Face> faces, Optional<Rotation> rotation,
                        boolean shade) {
        this(from, to, faces, rotation, shade, 0);
    }
    public ModelElement(Vec3d from, Vec3d to, Map<Direction, Face> faces, Optional<Rotation> rotation) {
        this(from, to, faces, rotation, true, 0);
    }

    public ModelElement(Vec3d from, Vec3d to, Map<Direction, Face> faces) {
        this(from, to, faces, Optional.empty(), true, 0);
    }

    public record Rotation(Vec3d origin, Direction.Axis axis, float angle, boolean rescale) {
        public static final Codec<Rotation> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Vec3d.CODEC.optionalFieldOf("origin", Vec3d.ZERO).forGetter(Rotation::origin),
                Direction.Axis.CODEC.fieldOf("axis").forGetter(Rotation::axis),
                Codec.FLOAT.fieldOf("angle").forGetter(Rotation::angle),
                Codec.BOOL.optionalFieldOf("rescale", false).forGetter(Rotation::rescale)
        ).apply(instance, Rotation::new));

        public Rotation(Vec3d origin, Direction.Axis axis, float angle) {
            this(origin, axis, angle, false);
        }

        public Rotation(Direction.Axis axis, float angle) {
            this(Vec3d.ZERO, axis, angle, false);
        }
    }

    public record Face(List<Float> uv, String texture, Optional<Direction> cullface, int rotation, int tintIndex) {
        public static final Codec<Face> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.list(Codec.FLOAT, 4, 4).optionalFieldOf("uv", List.of()).forGetter(Face::uv),
                Codec.STRING.optionalFieldOf("texture", "").forGetter(Face::texture),
                Direction.CODEC.optionalFieldOf("cullface").forGetter(Face::cullface),
                Codec.INT.optionalFieldOf("rotation", 0).forGetter(Face::rotation),
                Codec.INT.optionalFieldOf("tintindex", -1).forGetter(Face::tintIndex)
        ).apply(instance, Face::new));

        public Face {
            if (uv.size() != 4 && !uv.isEmpty()) {
                throw new IllegalArgumentException("uv needs to have either 4 elements or be empty");
            }
        }

        public Face(List<Float> uv, String texture, Optional<Direction> cullface, int rotation) {
            this(uv, texture, cullface, rotation, -1);
        }

        public Face(List<Float> uv, String texture, Optional<Direction> cullface) {
            this(uv, texture, cullface, 0, -1);
        }

        public Face(List<Float> uv, String texture) {
            this(uv, texture, Optional.empty(), 0, -1);
        }

        public Face(String texture) {
            this(List.of(), texture, Optional.empty(), 0, -1);
        }
    }
}
