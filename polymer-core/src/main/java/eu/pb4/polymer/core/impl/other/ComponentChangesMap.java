package eu.pb4.polymer.core.impl.other;

import net.minecraft.component.ComponentChanges;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public record ComponentChangesMap(ComponentChanges changes) implements ComponentMap {
    @Nullable
    @Override
    public <T> T get(ComponentType<? extends T> type) {
        var x = this.changes.get(type);
        //noinspection OptionalAssignedToNull
        return x != null ? x.orElse(null) : null;
    }

    @Override
    public Set<ComponentType<?>> getTypes() {
        var set = new HashSet<ComponentType<?>>();
        for (var entry : this.changes.entrySet()) {
            if (entry.getValue().isPresent()) {
                set.add(entry.getKey());
            }
        }
        return set;
    }
}
