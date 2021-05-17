package me.darrionat.serverselector.commands.subcommands;

import me.darrionat.pluginlib.commands.BaseCommand;
import me.darrionat.pluginlib.commands.SubCommand;
import me.darrionat.pluginlib.utils.Utils;
import me.darrionat.serverselector.ServerSelectorPlugin;
import me.darrionat.serverselector.interfaces.IMessageService;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends SubCommand {
    private final IMessageService messageService;

    private final List<String> helpMessages = new ArrayList<String>();
    private final int helpPagesAmt;

    public HelpCommand(BaseCommand parentCommand, ServerSelectorPlugin plugin, IMessageService messageService) {
        super(parentCommand, plugin);
        this.messageService = messageService;
        for (SubCommand subCommand : parentCommand.getSubCommands())
            helpMessages.add(messageService.getHelpMessage(subCommand));
        helpPagesAmt = (helpMessages.size() + 5 - 1) / 5;
    }

    public String getSubCommand() {
        return "help";
    }

    public int getRequiredArgs() {
        return 1;
    }

    public boolean onlyPlayers() {
        return false;
    }

    protected void runCommand(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sendHelpMessage(sender, "1");
        } else {
            sendHelpMessage(sender, args[1]);
        }
    }

    public void sendHelpMessage(CommandSender sender, String pageInput) {
        int page;
        try {
            page = Integer.parseInt(pageInput);
        } catch (NumberFormatException exe) {
            page = 1;
        }
        if (page > helpPagesAmt || page < 1) {
            page = 1;
        }

        messageService.sendHelpHeader(sender, page, helpPagesAmt);

        for (int i = page * 5 - 5; i <= (page * 5 - 1); i++) {
            if (i == (helpMessages.size())) {
                break;
            }
            sender.sendMessage(Utils.chat(" " + helpMessages.get(i)));
        }
    }
}