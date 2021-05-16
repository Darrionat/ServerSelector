package me.darrionat.serverselector.repositories;

import me.darrionat.pluginlib.files.Config;
import me.darrionat.pluginlib.files.ConfigBuilder;
import me.darrionat.pluginlib.utils.Utils;
import me.darrionat.serverselector.ServerSelectorPlugin;
import me.darrionat.serverselector.interfaces.IConfigRepository;
import me.darrionat.shaded.xseries.XMaterial;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ConfigRepository implements IConfigRepository {
    private static final XMaterial DEFAULT_SELECTOR = XMaterial.COMPASS;
    private static final String MATERIAL_DNE = "The selector material in the config.yml does not exist in Minecraft. Setting to default value " + DEFAULT_SELECTOR;

    private final ServerSelectorPlugin plugin;
    private final Config config;
    private FileConfiguration file;

    public ConfigRepository(ServerSelectorPlugin plugin) {
        this.plugin = plugin;
        ConfigBuilder builder = new ConfigBuilder(plugin, CONFIG);
        builder.useBuiltInFile();
        builder.updateConfig();
        config = builder.build();
        init();
    }

    public void init() {
        file = config.getFileConfiguration();
    }

    public String getSelectorName() {
        return file.getString("selector.name");
    }

    public XMaterial getSelectorMaterial() {
        String materialStr = file.getString("selector.material");
        try {
            return XMaterial.valueOf(materialStr);
        } catch (IllegalArgumentException e) {
            plugin.log("&c" + MATERIAL_DNE);
            return DEFAULT_SELECTOR;
        }
    }

    public List<String> getSelectorLore() {
        List<String> lore = new ArrayList<String>();
        for (String line : file.getStringList("selector.lore"))
            lore.add(Utils.chat(line));
        return lore;
    }

    public boolean removeDuplicates() {
        return file.getBoolean("inventory.removeDuplicates");
    }

    public boolean giveSelectorOnJoin() {
        return file.getBoolean("inventory.giveOnJoin");
    }
}