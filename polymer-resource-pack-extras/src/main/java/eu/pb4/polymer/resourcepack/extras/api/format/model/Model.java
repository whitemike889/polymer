package eu.pb4.polymer.resourcepack.extras.api.format.model;

import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ModelTransformationMode;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public record Model(Optional<Identifier> parent, Optional<List<ModelElement>> elements, Map<String, String> textures,
                    Map<ModelTransformationMode, ModelTransformation> display,
                    Optional<GuiLight> guiLight,
                    boolean ambientOcclusion) {
    public static final Codec<Model> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.optionalFieldOf("parent").forGetter(Model::parent),
            ModelElement.CODEC.listOf().optionalFieldOf("elements").forGetter(Model::elements),
            Codec.unboundedMap(Codec.STRING, Codec.STRING).optionalFieldOf("textures", Map.of()).forGetter(Model::textures),
            Codec.unboundedMap(ModelTransformationMode.CODEC, ModelTransformation.CODEC).optionalFieldOf("display", Map.of()).forGetter(Model::display),
            GuiLight.CODEC.optionalFieldOf("gui_light").forGetter(Model::guiLight),
            Codec.BOOL.optionalFieldOf("ambientocclusion", true).forGetter(Model::ambientOcclusion)
    ).apply(instance, Model::new));

    public Model(Optional<Identifier> parent, Optional<List<ModelElement>> elements, Map<String, String> textures,
                 Map<ModelTransformationMode, ModelTransformation> display,
                 Optional<GuiLight> guiLight) {
        this(parent, elements, textures, display, guiLight, true);
    }

    public Model(Optional<Identifier> parent, Optional<List<ModelElement>> elements, Map<String, String> textures,
                 Map<ModelTransformationMode, ModelTransformation> display) {
        this(parent, elements, textures, display, Optional.empty(), true);
    }

    public Model(Optional<Identifier> parent, Optional<List<ModelElement>> elements, Map<String, String> textures) {
        this(parent, elements, textures, Map.of(), Optional.empty(), true);
    }

    public Model(Identifier parent, Map<String, String> textures) {
        this(Optional.of(parent), Optional.empty(), textures, Map.of(), Optional.empty(), true);
    }

    public Model(List<ModelElement> elements, Map<String, String> textures) {
        this(Optional.empty(), Optional.of(elements), textures, Map.of(), Optional.empty(), true);
    }

    public String toJson() {
        return CODEC.encodeStart(JsonOps.INSTANCE, this).getOrThrow().toString();
    }

    public static Model fromJson(String json) {
        return CODEC.decode(JsonOps.INSTANCE, JsonParser.parseString(json)).getOrThrow().getFirst();
    }
}
