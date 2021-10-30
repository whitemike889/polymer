package eu.pb4.polymer.impl.networking;

import eu.pb4.polymer.api.utils.PolymerUtils;
import net.minecraft.util.Identifier;

public class PolymerPacketIds {
    public static final String VERSION = "version";
    public static final Identifier VERSION_ID = PolymerUtils.id(VERSION);

    public static final String REGISTRY_BLOCK = "registry/block";
    public static final Identifier REGISTRY_BLOCK_ID = PolymerUtils.id(REGISTRY_BLOCK);
    public static final String REGISTRY_BLOCKSTATE = "registry/blockstate";
    public static final Identifier REGISTRY_BLOCKSTATE_ID = PolymerUtils.id(REGISTRY_BLOCKSTATE);
    public static final String REGISTRY_CLEAR = "registry/clear";
    public static final Identifier REGISTRY_CLEAR_ID = PolymerUtils.id(REGISTRY_CLEAR);

    public static final String BLOCK_UPDATE = "world/block";
    public static final Identifier BLOCK_UPDATE_ID = PolymerUtils.id(BLOCK_UPDATE);
    public static final String CHUNK_SECTION_UPDATE = "world/section";
    public static final Identifier CHUNK_SECTION_UPDATE_ID = PolymerUtils.id(CHUNK_SECTION_UPDATE);
}