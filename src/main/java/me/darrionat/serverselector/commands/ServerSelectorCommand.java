package me.darrionat.serverselector.commands;

import me.darrionat.pluginlib.commands.BaseCommand;
import me.darrionat.serverselector.ServerSelectorPlugin;
import me.darrionat.serverselector.commands.subcommands.HelpCommand;
import me.darrionat.serverselector.commands.subcommands.OpenCommand;
import me.darrionat.serverselector.commands.subcommands.ReloadCommand;
import me.darrionat.serverselector.interfaces.IMessageService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ServerSelectorCommand extends BaseCommand {
    private final IMessageService messageService;

    public ServerSelectorCommand(ServerSelectorPlugin plugin, IMessageService messageService) {
        super(plugin);
        this.messageService = messageService;
        addSubCommand(new OpenCommand(this, plugin));
        addSubCommand(new HelpCommand(this, plugin, messageService));
        addSubCommand(new ReloadCommand(this, plugin, messageService));
    }

    public String getCommandLabel() {
        return "serverselector";
    }

    protected void runNoArgs(CommandSender sender, Command command, String label, String[] args) {
        messageService.sendBaseMessage(sender, this);
    }
}