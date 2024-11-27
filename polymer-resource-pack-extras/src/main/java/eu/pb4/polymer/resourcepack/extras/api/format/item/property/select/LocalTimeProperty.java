package eu.pb4.polymer.resourcepack.extras.api.format.item.property.select;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public record LocalTimeProperty(String pattern, String locale,
                                Optional<String> timeZone) implements SelectProperty<String> {
    public static final Type<LocalTimeProperty, String> TYPE = new Type<>(
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                            Codec.STRING.fieldOf("pattern").forGetter(LocalTimeProperty::pattern),
                            Codec.STRING.optionalFieldOf("locale", "").forGetter(LocalTimeProperty::locale),
                            Codec.STRING.optionalFieldOf("time_zone").forGetter(LocalTimeProperty::timeZone)
                    ).apply(instance, LocalTimeProperty::new)), Codec.STRING
    );

    @Override
    public Type<LocalTimeProperty, String> type() {
        return TYPE;
    }
}
