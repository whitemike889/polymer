package eu.pb4.polymer.resourcepack.extras.api.format.model;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;

public enum GuiLight implements StringIdentifiable {
    SIDE("side"),
    FRONT("front");

    public static final Codec<GuiLight> CODEC = StringIdentifiable.createCodec(GuiLight::values);

    private final String name;
    private GuiLight(String name) {
        this.name = name;
    }
    @Override
    public String asString() {
        return this.name;
    }
}
