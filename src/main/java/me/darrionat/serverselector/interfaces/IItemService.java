package me.darrionat.serverselector.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IItemService extends Service {
    /**
     * Checks to see if an item is a server selector.
     *
     * @param item the item to check.
     * @return returns {@code true} if the item is a server selector; {@code false} otherwise.
     */
    boolean isServerSelector(ItemStack item);

    /**
     * Adds a custom tag to the item to label it as a ServerSelector.
     *
     * @param item the item to be tagged as a server selector.
     * @return returns the item that is tagged a server selector.
     */
    ItemStack setTag(ItemStack item);

    /**
     * Gets the slot that the player's server selector is located in.
     *
     * @param p the player.
     * @return returns the slot of the server selector in the player's inventory.
     */
    int getSelectorSlot(Player p);

    /**
     * Gets a player's server selector.
     *
     * @param p the player to get the item of.
     * @return returns the player's server selector; {@code null} if the player doesn't have the custom item.
     */
    ItemStack getPlayerSelector(Player p);

    /**
     * Checks to see if a player has a server selector.
     *
     * @param p the player.
     * @return returns {@code true} if the player has a server selector; {@code false} otherwise.
     */
    boolean playerHasSelector(Player p);

    /**
     * Gives a player a server selector. If the player already has one, do nothing.
     *
     * @param p the player.
     */
    void giveSelectorToPlayer(Player p);

    /**
     * Gets the selector item.
     *
     * @return gets the server selector as an item.
     */
    ItemStack getSelector();

    /**
     * Removes all other selectors that are either duplicates of the current selector or do not have the same item
     * data.
     *
     * @param p the player for which to check duplicates.
     */
    void removeSelectorDuplicates(Player p);
}