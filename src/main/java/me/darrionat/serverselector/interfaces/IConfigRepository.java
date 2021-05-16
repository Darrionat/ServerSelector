package me.darrionat.serverselector.interfaces;

import me.darrionat.shaded.xseries.XMaterial;

import java.util.List;

public interface IConfigRepository extends Repository {
    String getSelectorName();

    XMaterial getSelectorMaterial();

    List<String> getSelectorLore();

    boolean removeDuplicates();

    boolean giveSelectorOnJoin();
}
