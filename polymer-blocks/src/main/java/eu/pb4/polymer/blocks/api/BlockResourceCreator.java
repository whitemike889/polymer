package eu.pb4.polymer.blocks.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import eu.pb4.polymer.blocks.impl.BlockExtBlockMapper;
import eu.pb4.polymer.blocks.impl.DefaultModelData;
import eu.pb4.polymer.blocks.impl.PolymerBlocksInternal;
import eu.pb4.polymer.common.impl.CompatStatus;
import eu.pb4.polymer.core.api.block.BlockMapper;
import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import eu.pb4.polymer.core.impl.PolymerImpl;
import eu.pb4.polymer.resourcepack.api.ResourcePackCreator;
import eu.pb4.polymer.resourcepack.impl.generation.DefaultRPBuilder;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Predicate;

public final class BlockResourceCreator {
    private static final PolymerBlockModel EMPTY = PolymerBlockModel.of(Identifier.of("polymer", "block/empty"));
    private final Map<BlockModelType, List<BlockState>> states;
    private final Set<Block> hasRequested = Collections.newSetFromMap(new IdentityHashMap<>());
    final Map<BlockState, PolymerBlockModel[]> models;
    private final ResourcePackCreator creator;
    private final Runnable onRegister;
    private final BlockExtBlockMapper blockMapper;

    private final EnumMap<BlockModelType, BlockState> emptyBlocks = new EnumMap<>(BlockModelType.class);

    private boolean registered = false;
    private boolean registeredEmpty = false;

    public static BlockResourceCreator of(ResourcePackCreator creator) {
        if (CompatStatus.POLYMC) {
            PolymerImpl.LOGGER.warn("Polymer Blocks non-global module might not work correctly with PolyMC! Be warned!");
        }

        return new BlockResourceCreator(creator, new BlockExtBlockMapper(BlockMapper.createDefault()), () -> {});
    }

    BlockResourceCreator(ResourcePackCreator creator, BlockExtBlockMapper blockMapper, Runnable onRegister) {
        this.states = new EnumMap<>(BlockModelType.class);
        DefaultModelData.USABLE_STATES.forEach((key, value) -> this.states.put(key, new ReferenceArrayList<>(value)));
        this.models = new IdentityHashMap<>(DefaultModelData.MODELS);
        this.creator = creator;
        this.blockMapper = blockMapper;
        this.onRegister = onRegister;
    }

    public BlockMapper getBlockMapper() {
        return this.blockMapper;
    }

    private void registerEvent() {
        if (!this.registered) {
            creator.creationEvent.register((b) -> {
                if (b instanceof DefaultRPBuilder defaultRPBuilder) {
                    defaultRPBuilder.buildEvent.register((c) -> this.generateResources(defaultRPBuilder, c));
                }
            });
            this.onRegister.run();
            this.registered = true;
        }
    }

    @Nullable
    public BlockState requestEmpty(BlockModelType type) {
        var x = this.emptyBlocks.get(type);
        if (x != null) {
            return x;
        }
        Predicate<BlockState> predicate = null;
        if (type.name().contains("TRAPDOOR")) {
            predicate = b -> b.isOf(Blocks.IRON_TRAPDOOR);
        } else if (type.name().contains("DOOR")) {
            predicate = b -> b.isOf(Blocks.IRON_TRAPDOOR);
        }  else if (type == BlockModelType.VINES_BLOCK) {
            predicate = b -> b.isOf(Blocks.TWISTING_VINES);
        }

        if (predicate != null) {
            x = requestBlock(type, predicate, EMPTY);
        }
        if (x == null) {
            x = requestBlock(type, EMPTY);
        }
        if (x == null) {
            return null;
        }
        this.emptyBlocks.put(type, x);
        if (!this.registeredEmpty) {
            this.registeredEmpty = true;
            this.creator.addAssetSource("polymer-blocks");
        }
        return x;
    }

    @Nullable
    public BlockState requestBlock(BlockModelType type, PolymerBlockModel... model) {
        return requestBlock(type, x -> true, model);
    }
    public BlockState requestBlock(BlockModelType type, Predicate<BlockState> predicate, PolymerBlockModel... model) {
        var states = this.states.get(type);
        if (!states.isEmpty()) {
            BlockState state = null;
            for (var s : states) {
                if (predicate.test(s)) {
                    state = s;
                    break;
                }
            }
            if (state == null) {
                return null;
            }
            states.remove(state);
            models.put(state, model);
            this.hasRequested.add(state.getBlock());
            this.registerEvent();

            if (state.getBlock() instanceof Waterloggable) {
                this.blockMapper.stateMap.put(state, DefaultModelData.SPECIAL_REMAPS
                        .getOrDefault(state, (state.getBlock() instanceof LeavesBlock
                                ? state.getBlock().getDefaultState().with(LeavesBlock.PERSISTENT, true) : state.getBlock().getDefaultState()).with(Properties.WATERLOGGED, state.get(Properties.WATERLOGGED)))
                );
            } else {
                this.blockMapper.stateMap.put(state, DefaultModelData.SPECIAL_REMAPS
                        .getOrDefault(state, state.getBlock() instanceof LeavesBlock
                                ? state.getBlock().getDefaultState().with(LeavesBlock.PERSISTENT, true) : state.getBlock().getDefaultState())
                );
            }

            return state;
        }
        return null;
    }

    public int getBlocksLeft(BlockModelType type) {
        return this.states.get(type).size();
    }

    private void generateResources(DefaultRPBuilder builder, List<String> credits) {
        if (CompatStatus.POLYMC && this == PolymerBlockResourceUtils.CREATOR) {
            // PolyMC's generation is better, so just leave it for now...
            return;
        }

        var map = new HashMap<String, HashMap<String, JsonArray>>();

        for (var blockStateEntry : this.models.entrySet()) {
            if (!this.hasRequested.contains(blockStateEntry.getKey().getBlock())) {
                continue;
            }
            var state = blockStateEntry.getKey();
            var models = blockStateEntry.getValue();

            var id = Registries.BLOCK.getId(state.getBlock());

            var stateName = PolymerBlocksInternal.generateStateName(state);
            var array = PolymerBlocksInternal.createJsonElement(models);

            map.computeIfAbsent("assets/" + id.getNamespace() + "/blockstates/" + id.getPath() + ".json", (s) -> new HashMap<>()).put(stateName, array);
        }

        for (var baseEntry : map.entrySet()) {
            try {
                var modelObject = new JsonObject();

                var variants = new JsonObject();

                var values = new ArrayList<>(baseEntry.getValue().entrySet());
                values.sort(Map.Entry.comparingByKey());
                for (var entries : values) {
                    variants.add(entries.getKey(), entries.getValue());
                }

                modelObject.add("variants", variants);

                builder.addData(baseEntry.getKey(), DefaultRPBuilder.GSON.toJson(modelObject).getBytes(StandardCharsets.UTF_8));
            } catch (Exception e) {
                PolymerImpl.LOGGER.warn("Exception occurred while building block model!", e);
            }
        }

    }
}
