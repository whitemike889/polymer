package eu.pb4.polymer.mixin.block.packet;

import eu.pb4.polymer.api.block.PolymerBlock;
import eu.pb4.polymer.api.block.PolymerBlockUtils;
import me.jellysquid.mods.lithium.common.world.chunk.LithiumHashPalette;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.chunk.ArrayPalette;
import net.minecraft.world.chunk.BiMapPalette;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = {ArrayPalette.class, BiMapPalette.class, LithiumHashPalette.class}, priority = 500)
public abstract class BlockPaletteMixin<T>  {
    @ModifyArg(method = {"toPacket", "getPacketSize"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/collection/IdList;getRawId(Ljava/lang/Object;)I"))
    public T polymer_getIdRedirect(T object) {
        if (object instanceof BlockState blockState) {
            Block block = blockState.getBlock();

            if (block instanceof PolymerBlock virtualBlock) {
                return (T) PolymerBlockUtils.getBlockStateSafely(virtualBlock, blockState);
            }
        }
        return object;
    }
}