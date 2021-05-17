package me.darrionat.serverselector.listeners;

import jdk.internal.org.objectweb.asm.Handle;
import me.darrionat.serverselector.ServerSelectorPlugin;
import me.darrionat.serverselector.interfaces.IConfigRepository;
import me.darrionat.serverselector.interfaces.IItemService;
import me.darrionat.serverselector.services.ItemService;
import me.darrionat.shaded.xseries.XItemStack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ListIterator;

public class PlayerJoin implements Listener {
    private final ServerSelectorPlugin plugin;
    private final IItemService itemService;
    private final IConfigRepository configRepo;

    public PlayerJoin(ServerSelectorPlugin plugin, IItemService itemService, IConfigRepository configRepo) {
        this.plugin = plugin;
        this.itemService = itemService;
        this.configRepo = configRepo;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!configRepo.giveSelectorOnJoin()) return;
        int slot = itemService.getSelectorSlot(p);
        // The player does not have a selector.
        if (slot == -1) {
            itemService.giveSelectorToPlayer(p);
            return;
        }
        assert p.getInventory().getItem(slot) != null;
        // If the player's selector is up to date, nothing is done.
        if (p.getInventory().getItem(slot).equals(itemService.getSelector())) return;

        // Handle updating the selector in the player's inventory if it has changed in the config
        ListIterator<ItemStack> iterator = p.getInventory().iterator();
        while (iterator.hasNext())
            if (itemService.isServerSelector(iterator.next()))
                iterator.remove();
        itemService.giveSelectorToPlayer(p);
    }
}