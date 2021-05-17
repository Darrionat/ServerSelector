package me.darrionat.serverselector.commands.subcommands;

import me.darrionat.pluginlib.commands.BaseCommand;
import me.darrionat.pluginlib.commands.SubCommand;
import me.darrionat.pluginlib.guis.GuiHandler;
import me.darrionat.serverselector.ServerSelectorPlugin;
import me.darrionat.serverselector.gui.SelectorUi;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenCommand extends SubCommand {
    private final GuiHandler guiHandler;
    private final SelectorUi ui;

    public OpenCommand(BaseCommand parentCommand, ServerSelectorPlugin plugin) {
        super(parentCommand, plugin);
        this.guiHandler = plugin.getGuiHandler();
        this.ui = plugin.getSelectorUi();
    }

    public String getSubCommand() {
        return "open";
    }

    public int getRequiredArgs() {
        return 1;
    }

    public boolean onlyPlayers() {
        return true;
    }

    protected void runCommand(CommandSender sender, String[] args) {
        guiHandler.openGui((Player) sender, ui);
    }
}