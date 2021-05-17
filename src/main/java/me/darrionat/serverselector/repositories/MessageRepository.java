package me.darrionat.serverselector.repositories;

import me.darrionat.pluginlib.commands.SubCommand;
import me.darrionat.pluginlib.files.Config;
import me.darrionat.pluginlib.files.ConfigBuilder;
import me.darrionat.serverselector.ServerSelectorPlugin;
import me.darrionat.serverselector.interfaces.IMessageRepository;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository implements IMessageRepository {
    private final Config config;
    private FileConfiguration file;
    private List<String> helpMessages = new ArrayList<String>();

    public MessageRepository(ServerSelectorPlugin plugin) {
        ConfigBuilder builder = new ConfigBuilder(plugin, MESSAGES);
        builder.useBuiltInFile();
        builder.updateConfig();
        config = builder.build();
        init();
    }

    public void init() {
        file = config.getFileConfiguration();
        helpMessages.clear();
        for (String subCommand : file.getConfigurationSection("help").getKeys(false))
            helpMessages.add(getHelpMessage(subCommand));
    }

    public List<String> getBaseMessage() {
        return file.getStringList("baseMessage");
    }

    public String getReloadMessage() {
        return file.getString("reload");
    }

    public String getHelpMessage(SubCommand subCommand) {
        return file.getString("help." + subCommand.getSubCommand());
    }

    public String getHelpMessage(String subCommand) {
        return file.getString("help." + subCommand);
    }

    public List<String> getHelpMessages() {
        return helpMessages;
    }

    public String getHelpHeader() {
        return file.getString("helpHeader");
    }

    public String getNoPermissionMessage() {
        return file.getString("errors.noPermission");
    }

    public String getOnlyPlayersMessage() {
        return file.getString("errors.onlyPlayers");
    }
}