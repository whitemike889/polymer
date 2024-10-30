package eu.pb4.polymer.networking.api.util;

import net.minecraft.network.listener.ClientCommonPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.PacketType;
import net.minecraft.server.network.ServerCommonNetworkHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public interface ServerDynamicPacket extends Packet<ClientCommonPacketListener> {
    static Packet<ClientCommonPacketListener> of(BiFunction<ServerCommonNetworkHandler, @Nullable ServerPlayerEntity, Packet<ClientCommonPacketListener>> builder) {
        return (ServerDynamicPacket) builder::apply;
    }

    Packet<ClientCommonPacketListener> createPacket(ServerCommonNetworkHandler handler, @Nullable ServerPlayerEntity player);

    @Override
    default PacketType<? extends Packet<ClientCommonPacketListener>> method_65080() {
        throw new UnsupportedOperationException();
    };

    @Override
    default void method_65081(ClientCommonPacketListener listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    default boolean isWritingErrorSkippable() {
        return true;
    }
}
