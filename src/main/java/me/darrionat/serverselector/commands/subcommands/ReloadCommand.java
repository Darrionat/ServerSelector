package me.darrionat.serverselector.commands.subcommands;

import me.darrionat.pluginlib.commands.BaseCommand;
import me.darrionat.pluginlib.commands.SubCommand;
import me.darrionat.serverselector.ServerSelectorPlugin;
import me.darrionat.serverselector.interfaces.IMessageService;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends SubCommand {
    private final ServerSelectorPlugin plugin;
    private final IMessageService messageService;

    public ReloadCommand(BaseCommand parentCommand, ServerSelectorPlugin plugin, IMessageService messageService) {
        super(parentCommand, plugin);
        this.plugin = plugin;
        this.messageService = messageService;
    }

    public String getSubCommand() {
        return "reload";
    }

    public int getRequiredArgs() {
        return 1;
    }

    public boolean onlyPlayers() {
        return false;
    }

    protected void runCommand(CommandSender sender, String[] args) {
        plugin.reinitializeRepositories();
        messageService.sendReloadMessage(sender);
    }
}