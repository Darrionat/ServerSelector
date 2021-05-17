package me.darrionat.serverselector.repositories;

import me.darrionat.pluginlib.files.Config;
import me.darrionat.pluginlib.files.ConfigBuilder;
import me.darrionat.pluginlib.utils.Utils;
import me.darrionat.serverselector.ServerSelectorPlugin;
import me.darrionat.serverselector.interfaces.IGuiRepository;
import me.darrionat.shaded.xseries.XMaterial;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuiRepository implements IGuiRepository {
    private static final XMaterial DEFAULT_FILLER = XMaterial.BLACK_STAINED_GLASS_PANE;
    private static final String FILLER_MATERIAL_DNE = "The filler material in the " + SERVERS_GUI + " does not exist in Minecraft. Setting to default value " + DEFAULT_FILLER;

    private static final XMaterial DEFAULT_SERVER_MATERIAL = XMaterial.OAK_PLANKS;
    private static final String SERVER_MATERIAL_DNE = "The server material in the " + SERVERS_GUI + " does not exist in Minecraft. Setting to default value " + DEFAULT_SERVER_MATERIAL;

    private final ServerSelectorPlugin plugin;
    private final Config config;
    private FileConfiguration file;

    public GuiRepository(ServerSelectorPlugin plugin) {
        this.plugin = plugin;
        ConfigBuilder builder = new ConfigBuilder(plugin, SERVERS_GUI);
        builder.useBuiltInFile();
        config = builder.build();
        init();
    }

    public void init() {
        file = config.getFileConfiguration();
    }

    public String getGuiName() {
        return Utils.chat(file.getString("name"));
    }

    public int getGuiRows() {
        return file.getInt("rows");
    }

    public boolean fillerItemEnabled() {
        return file.getBoolean("filler.enabled");
    }

    public XMaterial getFillerItem() {
        String materialStr = file.getString("filler.material");
        try {
            return XMaterial.valueOf(materialStr);
        } catch (IllegalArgumentException e) {
            plugin.log("&c" + FILLER_MATERIAL_DNE);
            return DEFAULT_FILLER;
        }
    }

    public List<String> getItems() {
        List<String> toReturn = new ArrayList<String>();
        for (String key : file.getKeys(false)) {
            if (key.equals("name") || key.equals("rows") || key.equals("filler")) continue;
            toReturn.add(key);
        }
        return toReturn;
    }

    public XMaterial getItemMaterial(String serverName) {
        String materialStr = file.getString(serverName + ".material");
        try {
            return XMaterial.valueOf(materialStr);
        } catch (IllegalArgumentException e) {
            plugin.log("&c" + SERVER_MATERIAL_DNE);
            return DEFAULT_SERVER_MATERIAL;
        }
    }

    public int getItemSlot(String serverName) {
        return file.getInt(serverName + ".slot");
    }

    public String getItemDisplayName(String serverName) {
        return Utils.chat(file.getString(serverName + ".name"));
    }

    public boolean loreEnabled(String serverName) {
        return file.getBoolean(serverName + ".lore.enabled");
    }

    public List<String> getItemLore(String serverName) {
        List<String> lore = new ArrayList<String>();
        for (String line : file.getStringList(serverName + "lore.lines"))
            lore.add(Utils.chat(line));
        return lore;
    }
}