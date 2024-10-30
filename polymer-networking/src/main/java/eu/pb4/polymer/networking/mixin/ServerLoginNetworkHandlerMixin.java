package eu.pb4.polymer.networking.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.authlib.GameProfile;
import eu.pb4.polymer.networking.impl.*;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.DisconnectionInfo;
import net.minecraft.network.NetworkPhase;
import net.minecraft.network.NetworkState;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.listener.ServerConfigurationPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.common.SyncedClientOptions;
import net.minecraft.network.packet.c2s.login.EnterConfigurationC2SPacket;
import net.minecraft.network.state.ConfigurationStates;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.*;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicReference;

@Mixin(ServerLoginNetworkHandler.class)
public abstract class ServerLoginNetworkHandlerMixin implements NetworkHandlerExtension {
    @Shadow @Final
    ClientConnection connection;

    @Shadow @Nullable private GameProfile profile;

    @Shadow public abstract void onEnterConfiguration(EnterConfigurationC2SPacket packet);

    @Shadow @Final
    MinecraftServer server;

    @Shadow public abstract void onDisconnected(DisconnectionInfo info);

    @Unique
    private boolean polymerNet$ignoreCall = false;

    @Nullable
    @Unique
    private AtomicReference<SyncedClientOptions> polymerNet$overrideOptions;

    @Override
    public long polymerNet$lastPacketUpdate(Identifier packet) {
        return 0;
    }

    @Override
    public void polymerNet$savePacketTime(Identifier packet) {
    }

    @Override
    public ClientConnection polymerNet$getConnection() {
        return this.connection;
    }

    @WrapWithCondition(method = "onEnterConfiguration", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/ClientConnection;transitionOutbound(Lnet/minecraft/network/NetworkState;)V"))
    private boolean dontDuplicateCalls(ClientConnection instance, NetworkState<?> newState) {
        return NetImpl.IS_DISABLED;
    }

    @WrapOperation(method = "onEnterConfiguration", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/ClientConnection;transitionInbound(Lnet/minecraft/network/NetworkState;Lnet/minecraft/network/listener/PacketListener;)V"))
    private void dontDuplicateCalls2(ClientConnection instance, NetworkState<PacketListener> state, PacketListener packetListener, Operation<Void> original) {
        if (NetImpl.IS_DISABLED) {
            original.call(instance, state, packetListener);
        } else {
            ((ClientConnectionAccessor) instance).setPacketListener(packetListener);
        }
    }

    @Inject(method = "onEnterConfiguration", at = @At("HEAD"), cancellable = true)
    private void polymerNet$prePlayHandshakeHackfest(EnterConfigurationC2SPacket packet, CallbackInfo ci) {
        if (this.polymerNet$ignoreCall || NetImpl.IS_DISABLED) {
            return;
        }
        ci.cancel();
        this.connection.transitionOutbound(ConfigurationStates.S2C);
        var defaultOptions = SyncedClientOptions.createDefault();
        EarlyConfigurationConnectionMagic.handle(this.profile, defaultOptions, (ServerLoginNetworkHandler) (Object) this, this.server, connection, (context) -> {
            ((ExtClientConnection) connection).polymerNet$wrongPacketConsumer(context.storedPackets()::add);

            if (connection.isOpen()) {
                this.polymerNet$ignoreCall = true;
                if (context.options().get() != defaultOptions) {
                    this.polymerNet$overrideOptions = context.options();
                }
                this.onEnterConfiguration(packet);
                ((ExtClientConnection) connection).polymerNet$wrongPacketConsumer(null);
                //this.connection.enableAutoRead();
                if (this.connection.getPacketListener() instanceof ServerConfigurationPacketListener listener) {
                    for (var packetx : context.storedPackets()) {
                        try {
                            //noinspection unchecked
                            ((Packet<ServerConfigurationPacketListener>) packetx).method_65081(listener);
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    @ModifyArg(method = "onEnterConfiguration", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerConfigurationNetworkHandler;<init>(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/network/ClientConnection;Lnet/minecraft/server/network/ConnectedClientData;)V"))
    private ConnectedClientData polymerNet$swapClientData(ConnectedClientData clientData) {
        if (this.polymerNet$overrideOptions != null) {
            return new ConnectedClientData(clientData.gameProfile(), clientData.latency(), this.polymerNet$overrideOptions.get(), clientData.transferred());
        }
        return clientData;
    }
}
