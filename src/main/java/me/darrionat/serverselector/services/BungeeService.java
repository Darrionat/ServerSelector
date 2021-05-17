package me.darrionat.serverselector.services;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.darrionat.serverselector.ServerSelectorPlugin;
import me.darrionat.serverselector.interfaces.IBungeeService;
import org.bukkit.entity.Player;

public class BungeeService implements IBungeeService {
    private final ServerSelectorPlugin plugin;

    public BungeeService(ServerSelectorPlugin plugin) {
        this.plugin = plugin;
    }

    public void setupChannel() {
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }

    public void disableChannel() {
        plugin.getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, "BungeeCord");
    }

    public void sendPlayerToServer(Player p, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
}