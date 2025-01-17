package eu.pb4.polymertest;

import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.item.consume.UseAction;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.List;

public class TestItem extends SimplePolymerItem {
    private Random random = Random.create();
    public TestItem(Item.Settings settings, Item virtualItem) {
        super(settings, virtualItem);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        context.getWorld().setBlockState(context.getBlockPos(), Registries.BLOCK.getRandom(this.random).get().value().getDefaultState());
        return super.useOnBlock(context);
    }

    @Override
    public Identifier getPolymerItemModel(ItemStack stack, PacketContext context) {
        return null;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.sendMessage(Text.literal("Use!" + hand), false);
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        var builder = new StringBuilder();
        for (int i = 0; i < 255; i++) {
            builder.append("I");
        }
        tooltip.add(Text.literal(builder.toString()));
    }

    @Override
    public ItemStack getPolymerItemStack(ItemStack itemStack, TooltipType tooltipType, PacketContext context) {
        var x = super.getPolymerItemStack(itemStack, tooltipType, context);
        x.set(DataComponentTypes.RARITY, Rarity.EPIC);
        x.set(DataComponentTypes.CONSUMABLE, new ConsumableComponent(context.getPlayer() != null && context.getPlayer().isCreative() ? Float.POSITIVE_INFINITY : 3, UseAction.BOW,
                Registries.SOUND_EVENT.getEntry(SoundEvents.INTENTIONALLY_EMPTY), false, List.of()));
        return x;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
