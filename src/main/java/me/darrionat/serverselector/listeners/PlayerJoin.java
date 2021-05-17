package me.darrionat.serverselector.listeners;

import me.darrionat.serverselector.ServerSelectorPlugin;
import me.darrionat.serverselector.interfaces.IConfigRepository;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    private final ServerSelectorPlugin plugin;
    private final IConfigRepository configRepo;

    public PlayerJoin(ServerSelectorPlugin plugin, IConfigRepository configRepo) {
        this.plugin = plugin;
        this.configRepo = configRepo;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!configRepo.giveSelectorOnJoin()) return;
        // TODO handle players joining
    }
}
