package eu.pb4.polymer.resourcepack.extras.api.format.atlas;

import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.dynamic.Codecs;

import java.util.List;
import java.util.function.Function;

public interface AtlasSource {
    Codec<AtlasSource> CODEC = Codec.lazyInitialized(() -> AtlasSource.TYPES.getCodec(Identifier.CODEC).dispatch(AtlasSource::codec, Function.identity()));
    Codecs.IdMapper<Identifier, MapCodec<? extends AtlasSource>> TYPES = Util.make(new Codecs.IdMapper<>(), m -> {
        m.put(Identifier.ofVanilla("single"), SingleAtlasSource.CODEC);
        m.put(Identifier.ofVanilla("directory"), DirectoryAtlasSource.CODEC);
        m.put(Identifier.ofVanilla("filter"), FilterAtlasSource.CODEC);
        m.put(Identifier.ofVanilla("unstitch"), UnstitchAtlasSource.CODEC);
        m.put(Identifier.ofVanilla("paletted_permutations"), PalettedPermutationsAtlasSource.CODEC);
    });

    MapCodec<? extends AtlasSource> codec();
}
