package me.darrionat.serverselector.interfaces;

import me.darrionat.shaded.xseries.XMaterial;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public interface IConfigRepository extends Repository {
    /**
     * Gets the name of the server selector item.
     *
     * @return the display name of the server selector item.
     */
    String getSelectorName();

    /**
     * Gets the material of the server selector.
     *
     * @return the material of the server selector.
     */
    XMaterial getSelectorMaterial();

    /**
     * Gets the lore of the server selector.
     *
     * @return the lore of the server selector.
     */
    List<String> getSelectorLore();

    /**
     * Checks to see if a player receives a server selector on {@link PlayerJoinEvent}.
     *
     * @return {@code true} if a player should receive a server selector when they join; {@code false} otherwise.
     */
    boolean giveSelectorOnJoin();
}