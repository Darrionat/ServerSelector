package me.darrionat.serverselector.interfaces;

import org.bukkit.entity.Player;

public interface IBungeeService extends Service {
    /**
     * Registers a plugin channel for Bungeecord.
     */
    void setupChannel();

    /**
     * Unregisters a plugin channel for Bungeecord.
     */
    void disableChannel();

    /**
     * Attempts to send a player to a given server
     *
     * @param p          the player to send.
     * @param serverName the server to send the player to.
     */
    void sendPlayerToServer(Player p, String serverName);
}