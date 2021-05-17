package me.darrionat.serverselector.services;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import me.darrionat.serverselector.interfaces.IConfigRepository;
import me.darrionat.serverselector.interfaces.IItemService;
import me.darrionat.serverselector.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemService implements IItemService {
    private IConfigRepository configRepo;
    private final static String KEY = "ServerSelector";

    public ItemService(IConfigRepository configRepo) {
        this.configRepo = configRepo;
    }

    public boolean isServerSelector(ItemStack item) {
        return NBTEditor.contains(item, KEY);
    }

    public ItemStack setTag(ItemStack item) {
        item = NBTEditor.set(item, true, KEY);
        return item;
    }

    public int getSelectorSlot(Player p) {
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            ItemStack item = p.getInventory().getItem(i);
            if (item == null) continue;
            if (isServerSelector(item))
                return i;
        }
        return -1;
    }

    public ItemStack getPlayerSelector(Player p) {
        int slot = getSelectorSlot(p);
        if (getSelectorSlot(p) == -1)
            return null;
        return p.getInventory().getItem(slot);
    }

    public boolean playerHasSelector(Player p) {
        return getPlayerSelector(p) != null;
    }

    public void giveSelectorToPlayer(Player p) {
        ItemStack selector = getSelector();
        Utils.giveItemToPlayer(p, selector);
    }

    public ItemStack getSelector() {
        ItemStack selector = configRepo.getSelectorMaterial().parseItem();
        ItemMeta meta = selector.getItemMeta();
        meta.setDisplayName(configRepo.getSelectorName());
        meta.setLore(configRepo.getSelectorLore());
        selector.setItemMeta(meta);
        return selector;
    }

    public void removeSelectorDuplicates(Player p) {
        /**
         * TODO implement
         * If the selector does not match #getSelector, remove all the time
         * If the player has more than one selector, remove all and then give a new one
         */
    }
}