package eu.pb4.polymer.core.mixin.other;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.serialization.Codec;
import eu.pb4.polymer.common.api.PolymerCommonUtils;
import eu.pb4.polymer.core.api.entity.PolymerEntityUtils;
import eu.pb4.polymer.core.api.item.PolymerItemUtils;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;
import java.util.function.Function;

@Mixin(HoverEvent.class)
public abstract class HoverEventMixin {
    @ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/Codec;xmap(Ljava/util/function/Function;Ljava/util/function/Function;)Lcom/mojang/serialization/Codec;"))
    private static Codec<HoverEvent> patchCodec(Codec<HoverEvent> codec) {
        return codec.xmap(Function.identity(), content -> {
            if (PolymerCommonUtils.isServerNetworkingThread()) {
                if (content.getAction() == HoverEvent.Action.SHOW_ITEM) {
                    var stack = Objects.requireNonNull(content.getValue(HoverEvent.Action.SHOW_ITEM)).asStack();
                    return new HoverEvent(HoverEvent.Action.SHOW_ITEM,
                            new HoverEvent.ItemStackContent(PolymerItemUtils.getPolymerItemStack(stack, PolymerCommonUtils.getPlayerContext())));
                } else if (content.getAction() == HoverEvent.Action.SHOW_ENTITY) {
                    var val = Objects.requireNonNull(content.getValue(HoverEvent.Action.SHOW_ENTITY));
                    if (PolymerEntityUtils.isRegisteredEntityType(val.entityType)) {
                        return new HoverEvent(HoverEvent.Action.SHOW_TEXT, Texts.join(val.asTooltip(), Text.literal("\n")));
                    }
                }
            }
            return content;
        });
    }
}