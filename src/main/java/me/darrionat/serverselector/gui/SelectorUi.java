package me.darrionat.serverselector.gui;

import me.darrionat.pluginlib.Plugin;
import me.darrionat.pluginlib.guis.Gui;
import me.darrionat.serverselector.interfaces.IGuiRepository;
import me.darrionat.shaded.xseries.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.HashMap;
import java.util.List;

public class SelectorUi extends Gui {
    private IGuiRepository guiRepo;
    /**
     * Maps what server is in a specified slot.
     */
    private final HashMap<Integer, String> serverSlotMap = new HashMap<Integer, String>();

    public SelectorUi(Plugin plugin, IGuiRepository guiRepo) {
        super(plugin, guiRepo.getGuiName(), guiRepo.getGuiRows());
        this.guiRepo = guiRepo;
        init();
    }

    /**
     * Designates what servers belong to what slots
     */
    private void init() {
        for (String server : guiRepo.getItems()) {
            int slot = guiRepo.getItemSlot(server);
            serverSlotMap.put(slot, server);
        }
    }

    protected void getContents(Player p) {
        for (String server : guiRepo.getItems()) {
            List<String> lore = guiRepo.loreEnabled(server) ? guiRepo.getItemLore(server) : null;
            createItem(guiRepo.getItemMaterial(server), 1, guiRepo.getItemSlot(server),
                    guiRepo.getItemDisplayName(server), lore);
        }
    }

    public void clicked(Player p, int slot, ClickType clickType) {
        if (!serverSlotMap.containsKey(slot)) return;
        String server = serverSlotMap.get(slot);
        // TODO
        // Attempt to send the player to that server
    }
}