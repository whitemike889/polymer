package eu.pb4.polymer.autohost.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.SerializedName;
import eu.pb4.polymer.common.impl.CommonImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AutoHostConfig {
    public String _c1 = "Enables Polymer's ResourcePack Auto Hosting";
    @SerializedName("enabled")
    public boolean enabled = CommonImpl.DEV_ENV;
    public String _c2 = "Marks resource pack as required";
    @SerializedName("required")
    public boolean require = false;
    public String _c3 = "Mods may override the above setting and make the resource pack required, set this to false to disable that.";
    @SerializedName("mod_override")
    public boolean modOverride = true;
    public String _c4 = "Type of resource pack provider. Default: 'polymer:automatic'";
    public String type = "polymer:automatic";
    public String _c5 = "Configuration of type, see provider's source for more details";
    @SerializedName("settings")
    public JsonElement providerSettings = new JsonObject();
    public String _c6 = "Message sent to clients before pack is loaded";
    public JsonElement message = new JsonPrimitive("This server uses resource pack to enhance gameplay with custom textures and models. It might be unplayable without them.");
    public String _c7 = "Disconnect message in case of failure";
    @SerializedName("disconnect_message")
    public JsonElement disconnectMessage = new JsonPrimitive("Couldn't apply server resourcepack!");

    @SerializedName("external_resource_packs")
    public List<ExternalResourcePack> externalResourcePacks = new ArrayList<>();
    @SerializedName("setup_early")
    public boolean loadEarly = CommonImpl.DEV_ENV;

    public static class ExternalResourcePack {
        public UUID id;
        public String url;
        public String hash;
    }
}