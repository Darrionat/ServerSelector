package me.darrionat.serverselector.services;

import me.darrionat.pluginlib.commands.BaseCommand;
import me.darrionat.pluginlib.commands.SubCommand;
import me.darrionat.pluginlib.utils.Utils;
import me.darrionat.serverselector.ServerSelectorPlugin;
import me.darrionat.serverselector.interfaces.IMessageRepository;
import me.darrionat.serverselector.interfaces.IMessageService;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class MessageService implements IMessageService {
    private final ServerSelectorPlugin plugin;
    private final IMessageRepository messageRepo;

    public MessageService(ServerSelectorPlugin plugin, IMessageRepository messageRepo) {
        this.plugin = plugin;
        this.messageRepo = messageRepo;
    }

    public void sendBaseMessage(CommandSender sender, BaseCommand baseCommand) {
        for (String s : messageRepo.getBaseMessage()) {
            s = s.replace("%plugin%", plugin.getName());
            s = s.replace("%version%", plugin.getDescription().getVersion());
            s = s.replace("%baseCommand%", baseCommand.getCommandLabel());
            sendMessage(sender, s);
        }
    }

    public void sendReloadMessage(CommandSender sender) {
        sendMessage(sender, messageRepo.getReloadMessage());
    }

    public List<String> getHelpMessages() {
        return messageRepo.getHelpMessages();
    }

    public void sendHelpHeader(CommandSender sender, int page, int helpPagesAmt) {
        String header = messageRepo.getHelpHeader();
        header = header.replace("%page%", String.valueOf(page));
        header = header.replace("%pageAmount%", String.valueOf(helpPagesAmt));
        sendMessage(sender, header);
    }

    public void sendNoPermissionMessage(Player p, String permission) {
        sendMessage(p, messageRepo.getNoPermissionMessage().replace("%permission%", permission));
    }

    public void sendOnlyPlayersMessage(CommandSender sender) {
        sendMessage(sender, messageRepo.getOnlyPlayersMessage());
    }

    public void sendNotEnoughArgsMessage(SubCommand subCommand, CommandSender sender) {
        sendMessage(sender, messageRepo.getHelpMessage(subCommand));
    }

    private void sendMessage(CommandSender sender, String msg) {
        sender.sendMessage(Utils.chat(msg));
    }
}