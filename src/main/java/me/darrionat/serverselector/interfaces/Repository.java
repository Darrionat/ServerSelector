package me.darrionat.serverselector.interfaces;

public interface Repository {
    String CONFIG = "config.yml";
    String SERVERS_GUI = "serversGui.yml";
    String MESSAGES = "messages.yml";

    void init();
}