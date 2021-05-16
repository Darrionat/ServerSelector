package me.darrionat.serverselector.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Utils {
    /**
     * Gives a player an item. If the item does not fit within their inventory, the item will be dropped on the ground
     * at the player's location.
     *
     * @param p          the player.
     * @param itemToGive the item to give the player.
     */
    public static void giveItemToPlayer(Player p, ItemStack itemToGive) {
        // Adds the available items to the player's inventory
        // Returns which items could not be added
        HashMap<Integer, ItemStack> returnedItems = p.getInventory().addItem(itemToGive);

        for (ItemStack item : returnedItems.values())
            p.getWorld().dropItemNaturally(p.getLocation(), item);
    }
}
