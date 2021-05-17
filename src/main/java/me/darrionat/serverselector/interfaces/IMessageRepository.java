package me.darrionat.serverselector.interfaces;

import me.darrionat.pluginlib.commands.SubCommand;

import java.util.List;

public interface IMessageRepository extends Repository {
    List<String> getBaseMessage();

    String getReloadMessage();

    String getHelpMessage(SubCommand subCommand);

    String getHelpMessage(String subCommand);

    List<String> getHelpMessages();

    String getHelpHeader();

    String getNoPermissionMessage();

    String getOnlyPlayersMessage();
}