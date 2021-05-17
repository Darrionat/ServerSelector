package me.darrionat.serverselector.statics;

import me.darrionat.serverselector.ServerSelectorPlugin;
import me.darrionat.serverselector.interfaces.*;
import me.darrionat.serverselector.repositories.ConfigRepository;
import me.darrionat.serverselector.repositories.GuiRepository;
import me.darrionat.serverselector.repositories.MessageRepository;
import me.darrionat.serverselector.services.BungeeService;
import me.darrionat.serverselector.services.ItemService;
import me.darrionat.serverselector.services.MessageService;

import java.util.ArrayList;
import java.util.List;

public class Bootstrapper {
    private static Bootstrapper instance;
    /*
    Repositories
     */
    private final List<Repository> repositories = new ArrayList<Repository>();
    private IConfigRepository configRepo;
    private IGuiRepository guiRepo;
    private IMessageRepository messageRepo;
    /*
    Services
     */
    private final List<Service> services = new ArrayList<Service>();
    private IBungeeService bungeeService;
    private IItemService itemService;
    private IMessageService messageService;

    private Bootstrapper() {
    }

    public void init(ServerSelectorPlugin plugin) {
        configRepo = new ConfigRepository(plugin);
        guiRepo = new GuiRepository(plugin);
        messageRepo = new MessageRepository(plugin);
        repositories.add(configRepo);
        repositories.add(guiRepo);
        repositories.add(messageRepo);

        bungeeService = new BungeeService(plugin);
        itemService = new ItemService(configRepo);
        messageService = new MessageService(plugin, messageRepo);
        services.add(bungeeService);
        services.add(itemService);
        services.add(messageService);
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public List<Service> getServices() {
        return services;
    }

    public IConfigRepository getConfigRepo() {
        return configRepo;
    }

    public IGuiRepository getGuiRepo() {
        return guiRepo;
    }

    public IMessageRepository getMessageRepo() {
        return messageRepo;
    }

    public IBungeeService getBungeeService() {
        return bungeeService;
    }

    public IItemService getItemService() {
        return itemService;
    }

    public IMessageService getMessageService() {
        return messageService;
    }

    public static Bootstrapper getBootstrapper() {
        if (instance == null)
            instance = new Bootstrapper();
        return instance;
    }
}