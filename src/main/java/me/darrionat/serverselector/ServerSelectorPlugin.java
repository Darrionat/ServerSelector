package me.darrionat.serverselector;

import me.darrionat.pluginlib.ErrorHandler;
import me.darrionat.pluginlib.Plugin;

public class ServerSelectorPlugin extends Plugin {

    public void initPlugin() {

    }

    public void onDisable() {
        // TODO unregister outgoing bungee channel in IBungeeService
    }

    // TODO make an error handler, mainly for commands
    public ErrorHandler getErrorHandler() {
        return null;
    }
}