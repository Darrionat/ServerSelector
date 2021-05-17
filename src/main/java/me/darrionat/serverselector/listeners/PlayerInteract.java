package me.darrionat.serverselector.listeners;

import me.darrionat.pluginlib.guis.GuiHandler;
import me.darrionat.serverselector.ServerSelectorPlugin;
import me.darrionat.serverselector.gui.SelectorUi;
import me.darrionat.serverselector.interfaces.IItemService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {
    private final IItemService itemService;
    private final GuiHandler guiHandler;
    private final SelectorUi selectorUi;

    public PlayerInteract(ServerSelectorPlugin plugin, IItemService itemService) {
        this.itemService = itemService;
        this.guiHandler = plugin.getGuiHandler();
        this.selectorUi = plugin.getSelectorUi();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        @SuppressWarnings({"deprecation"})
        ItemStack item = p.getItemInHand();
        if (item.getType() == Material.AIR) return;
        if (!itemService.isServerSelector(item)) return;
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
            guiHandler.openGui(p, selectorUi);
    }
}