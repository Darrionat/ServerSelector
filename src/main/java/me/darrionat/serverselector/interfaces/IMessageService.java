package me.darrionat.serverselector.interfaces;

import me.darrionat.pluginlib.commands.BaseCommand;
import me.darrionat.pluginlib.commands.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface IMessageService extends Service {
    /**
     * Sent if no arguments are provided for the command.
     * <p>
     * Placeholders
     * <p>
     * {@code %plugin%} The plugin's name
     * <p>
     * {@code %version%} The plugin's version
     * <p>
     * {@code %baseCommand%} The base command label.
     *
     * @param sender      the sender of the command.
     * @param baseCommand the base command that was sent.
     */
    void sendBaseMessage(CommandSender sender, BaseCommand baseCommand);

    /**
     * Sent when the config files are reloaded.
     *
     * @param sender the sender of the command.
     */
    void sendReloadMessage(CommandSender sender);

    /**
     * Gets a help messages in the config.
     *
     * @param subCommand the subcommand.
     * @return a command help message.
     */
    String getHelpMessage(SubCommand subCommand);

    /**
     * The header to the help command's messages.
     * <p>
     * Placeholders
     * <p>
     * {@code %page%} The page that is being displayed
     * <p>
     * {@code %pageAmount%} The total amount of pages.
     *
     * @param sender       the sender of the command.
     * @param page         page of the help command.
     * @param helpPagesAmt the total amount of pages in the help command.
     */
    void sendHelpHeader(CommandSender sender, int page, int helpPagesAmt);

    /**
     * Sent when a player does not have a permission.
     * <p>
     * Placeholders
     * <p>
     * {@code %permission%} Equal to the {@code permission} parameter.
     *
     * @param p          the player.
     * @param permission the permission the player lacks.
     */
    void sendNoPermissionMessage(Player p, String permission);

    /**
     * Sent when the console runs a command that belongs to only players.
     *
     * @param sender the console.
     */
    void sendOnlyPlayersMessage(CommandSender sender);

    /**
     * Sent when not enough arguments were provided for a sub command.
     *
     * @param subCommand the subcommand.
     * @param sender     the sender of the command.
     */
    void sendNotEnoughArgsMessage(SubCommand subCommand, CommandSender sender);
}