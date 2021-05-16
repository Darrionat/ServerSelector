package me.darrionat.serverselector.interfaces;

import me.darrionat.shaded.xseries.XMaterial;

import java.util.List;

public interface IGuiRepository extends Repository {
    String getGuiName();

    int getGuiRows();

    boolean fillerItemEnabled();

    XMaterial getFillerItem();

    List<String> getItems();

    XMaterial getItemMaterial(String serverName);

    int getItemSlot(String serverName);

    String getItemDisplayName(String serverName);

    boolean loreEnabled(String serverName);

    List<String> getItemLore(String serverName);
}