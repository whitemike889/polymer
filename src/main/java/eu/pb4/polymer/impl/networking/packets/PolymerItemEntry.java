package eu.pb4.polymer.impl.networking.packets;

import eu.pb4.polymer.api.item.PolymerItemGroup;
import eu.pb4.polymer.api.item.PolymerItemUtils;
import eu.pb4.polymer.impl.InternalServerRegistry;
import eu.pb4.polymer.impl.compat.ServerTranslationUtils;
import eu.pb4.polymer.mixin.item.MiningToolItemAccessor;
import eu.pb4.polymer.mixin.other.ItemGroupAccessor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.ApiStatus;

import static eu.pb4.polymer.api.utils.PolymerUtils.id;

@ApiStatus.Internal
public record PolymerItemEntry(
        Identifier identifier,
        String itemGroup,
        ItemStack representation,
        int foodLevels,
        float saturation,
        Identifier miningTool,
        int miningLevel
) implements BufferWritable {
    public static final Identifier NOT_TOOL = id("not_tool");

    public void write(PacketByteBuf buf, int version, ServerPlayNetworkHandler handler) {
        if (version > -1) {
            buf.writeIdentifier(identifier);
            buf.writeString(itemGroup);
            buf.writeItemStack(ServerTranslationUtils.parseFor(handler, representation));

            if (version > 0) {
                buf.writeVarInt(foodLevels);
                buf.writeFloat(saturation);
                buf.writeIdentifier(miningTool);
                buf.writeVarInt(miningLevel);
            }
        }
    }

    public static PolymerItemEntry of(Item item, ServerPlayNetworkHandler handler) {
        var group = item.getGroup();

        var groupIdentifier = group != null
                ? group instanceof PolymerItemGroup pGroup
                ? pGroup.getId().toString()
                : ((ItemGroupAccessor) group).getId()
                : InternalServerRegistry.POLYMER_ITEM_GROUP.toString();

        var toolItem = item instanceof MiningToolItem x ? x : null;
        var food = item.getFoodComponent();

        return new PolymerItemEntry(
                Registry.ITEM.getId(item),
                groupIdentifier,
                PolymerItemUtils.getPolymerItemStack(item.getDefaultStack(), handler.player),
                food != null ? food.getHunger() : 0,
                food != null ? food.getSaturationModifier() : 0,
                toolItem != null ? ((MiningToolItemAccessor) toolItem).getEffectiveBlocks() instanceof Tag.Identified tag ? tag.getId() : NOT_TOOL : NOT_TOOL,
                toolItem != null  ? toolItem.getMaterial().getMiningLevel() : 0
        );
    }

    public static PolymerItemEntry read(PacketByteBuf buf, int version) {
        return switch (version) {
            case 0 -> new PolymerItemEntry(buf.readIdentifier(), buf.readString(), buf.readItemStack(), 0, 0, NOT_TOOL, 0);
            case 1 -> new PolymerItemEntry(buf.readIdentifier(), buf.readString(), buf.readItemStack(), buf.readVarInt(), buf.readFloat(), buf.readIdentifier(), buf.readVarInt());
            default -> null;
        };
    }
}
