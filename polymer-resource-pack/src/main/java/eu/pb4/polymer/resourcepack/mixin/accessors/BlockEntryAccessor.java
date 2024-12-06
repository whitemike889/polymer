package eu.pb4.polymer.resourcepack.mixin.accessors;

import net.minecraft.resource.metadata.BlockEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Optional;
import java.util.regex.Pattern;

@Mixin(BlockEntry.class)
public interface BlockEntryAccessor {
    @Invoker("<init>")
    static BlockEntry createBlockEntry(Optional<Pattern> namespace, Optional<Pattern> path) {
        throw new UnsupportedOperationException();
    }
}
