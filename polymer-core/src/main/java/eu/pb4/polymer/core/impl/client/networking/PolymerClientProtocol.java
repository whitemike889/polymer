package eu.pb4.polymer.core.impl.client.networking;

import eu.pb4.polymer.core.impl.client.InternalClientRegistry;
import eu.pb4.polymer.core.impl.networking.C2SPackets;
import eu.pb4.polymer.core.impl.networking.payloads.c2s.PolymerChangeTooltipC2SPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.ApiStatus;


@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class PolymerClientProtocol {
    public static void sendTooltipContext(ClientPlayNetworkHandler handler) {
        if (InternalClientRegistry.getClientProtocolVer(C2SPackets.CHANGE_TOOLTIP) != -1) {
            InternalClientRegistry.delayAction(C2SPackets.CHANGE_TOOLTIP.toString(), 200, () -> {
                handler.sendPacket(new CustomPayloadC2SPacket(new PolymerChangeTooltipC2SPayload(MinecraftClient.getInstance().options.advancedItemTooltips)));
            });
        }
    }
}
