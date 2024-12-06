package eu.pb4.polymer.virtualentity.api.attachment;

import eu.pb4.polymer.virtualentity.api.ElementHolder;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

public final class ManualAttachment implements HolderAttachment {
    private final ElementHolder holder;
    private final ServerWorld world;
    private final Supplier<Vec3d> posSupplier;

    public ManualAttachment(ElementHolder holder, ServerWorld world, Supplier<Vec3d> posSupplier) {
        this.holder = holder;
        this.world = world;
        this.posSupplier = posSupplier;
        holder.setAttachment(this);
    }

    @Override
    public void destroy() {
        if (this.holder.getAttachment() == this) {
            this.holder.setAttachment(null);
        }
    }

    @Override
    public Vec3d getPos() {
        return this.posSupplier.get();
    }

    @Override
    public ServerWorld getWorld() {
        return this.world;
    }

    @Override
    public void updateCurrentlyTracking(Collection<ServerPlayNetworkHandler> currentlyTracking) {
    }

    @Override
    public void updateTracking(ServerPlayNetworkHandler tracking) {
    }

    @Override
    public ElementHolder holder() {
        return holder;
    }

    public ServerWorld world() {
        return world;
    }

    public Supplier<Vec3d> posSupplier() {
        return posSupplier;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ManualAttachment) obj;
        return Objects.equals(this.holder, that.holder) &&
                Objects.equals(this.world, that.world) &&
                Objects.equals(this.posSupplier, that.posSupplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holder, world, posSupplier);
    }

    @Override
    public String toString() {
        return "ManualAttachment[" +
                "holder=" + holder + ", " +
                "world=" + world + ", " +
                "posSupplier=" + posSupplier + ']';
    }

}
