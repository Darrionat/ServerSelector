package me.darrionat.serverselector.interfaces;

public interface Repository {
    String CONFIG = "config.yml";
    String SERVERS = "serversGui.yml";

    void init();
}