package me.darrionat.serverselector.gui;

import me.darrionat.pluginlib.Plugin;
import me.darrionat.pluginlib.guis.Gui;
import me.darrionat.serverselector.interfaces.IBungeeService;
import me.darrionat.serverselector.interfaces.IGuiRepository;
import me.darrionat.shaded.xseries.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.HashMap;
import java.util.List;

public class SelectorUi extends Gui {
    private final IGuiRepository guiRepo;
    private final IBungeeService bungeeService;
    /**
     * Maps what server is in a specified slot.
     */
    private final HashMap<Integer, String> serverSlotMap = new HashMap<Integer, String>();

    public SelectorUi(Plugin plugin, IGuiRepository guiRepo, IBungeeService bungeeService) {
        super(plugin, guiRepo.getGuiName(), guiRepo.getGuiRows());
        this.guiRepo = guiRepo;
        this.bungeeService = bungeeService;
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
        if (guiRepo.fillerItemEnabled())
            fillerItem();
        for (String server : guiRepo.getItems()) {
            List<String> lore = guiRepo.loreEnabled(server) ? guiRepo.getItemLore(server) : null;
            createItem(guiRepo.getItemMaterial(server), 1, guiRepo.getItemSlot(server),
                    guiRepo.getItemDisplayName(server), lore);
        }
    }

    private void fillerItem() {
        XMaterial fillItem = guiRepo.getFillerItem();
        for (int i = 0; i < size; i++)
            createItem(fillItem, 1, i, " ");
    }

    public void clicked(Player p, int slot, ClickType clickType) {
        if (!serverSlotMap.containsKey(slot)) return;
        String server = serverSlotMap.get(slot);
        bungeeService.sendPlayerToServer(p, server);
    }
}