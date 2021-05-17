package me.darrionat.serverselector.utils;

import me.darrionat.pluginlib.ErrorHandler;
import me.darrionat.pluginlib.commands.SubCommand;
import me.darrionat.serverselector.interfaces.IMessageService;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Errors implements ErrorHandler {
    private IMessageService messageService;

    public Errors(IMessageService messageService) {
        this.messageService = messageService;
    }

    public void noPermissionError(Player p, String permission) {
        messageService.sendNoPermissionMessage(p, permission);
    }

    public void onlyPlayerCommandError(CommandSender sender) {
        messageService.sendOnlyPlayersMessage(sender);
    }

    public void notEnoughArguments(SubCommand subCommand, CommandSender sender) {
        messageService.sendNotEnoughArgsMessage(subCommand, sender);
    }
}