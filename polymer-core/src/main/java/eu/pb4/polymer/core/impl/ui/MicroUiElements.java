package eu.pb4.polymer.core.impl.ui;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Unit;

public class MicroUiElements {
    public static final MicroUi.PlayerClickAction EMPTY_ACTION = (player, slotIndex, button, actionType) -> { };

    public static final ItemStack EMPTY;
    public static final ItemStack BUTTON_PREVIOUS;
    public static final ItemStack BUTTON_PREVIOUS_LOCK;
    public static final ItemStack BUTTON_NEXT;
    public static final ItemStack BUTTON_NEXT_LOCK;
    public static final ItemStack BUTTON_BACK;
    public static final ItemStack BUTTON_SEARCH;

    static {
        EMPTY = Items.GRAY_STAINED_GLASS_PANE.getDefaultStack();
        EMPTY.set(DataComponentTypes.HIDE_TOOLTIP, Unit.INSTANCE);

        BUTTON_PREVIOUS = Items.GREEN_STAINED_GLASS_PANE.getDefaultStack();
        BUTTON_PREVIOUS.set(DataComponentTypes.CUSTOM_NAME, Text.translatable("spectatorMenu.previous_page").setStyle(Style.EMPTY.withItalic(false).withColor(Formatting.GREEN)));

        BUTTON_PREVIOUS_LOCK = Items.WHITE_STAINED_GLASS_PANE.getDefaultStack();
        BUTTON_PREVIOUS_LOCK.set(DataComponentTypes.CUSTOM_NAME, Text.translatable("spectatorMenu.previous_page").setStyle(Style.EMPTY.withItalic(false).withColor(Formatting.DARK_GRAY)));

        BUTTON_NEXT = Items.GREEN_STAINED_GLASS_PANE.getDefaultStack();
        BUTTON_NEXT.set(DataComponentTypes.CUSTOM_NAME, Text.translatable("spectatorMenu.next_page").setStyle(Style.EMPTY.withItalic(false).withColor(Formatting.GREEN)));

        BUTTON_NEXT_LOCK = Items.WHITE_STAINED_GLASS_PANE.getDefaultStack();
        BUTTON_NEXT_LOCK.set(DataComponentTypes.CUSTOM_NAME, Text.translatable("spectatorMenu.next_page").setStyle(Style.EMPTY.withItalic(false).withColor(Formatting.DARK_GRAY)));

        BUTTON_BACK = Items.BARRIER.getDefaultStack();
        BUTTON_BACK.set(DataComponentTypes.CUSTOM_NAME, Text.translatable("gui.back").setStyle(Style.EMPTY.withItalic(false).withColor(Formatting.RED)));

        BUTTON_SEARCH = Items.COMPASS.getDefaultStack();
        BUTTON_SEARCH.set(DataComponentTypes.CUSTOM_NAME, Text.translatable("itemGroup.search").setStyle(Style.EMPTY.withItalic(false)));
    }
}
