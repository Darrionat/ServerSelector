package me.darrionat.serverselector.statics;

import me.darrionat.serverselector.ServerSelectorPlugin;
import me.darrionat.serverselector.interfaces.IBungeeService;
import me.darrionat.serverselector.interfaces.IConfigRepository;
import me.darrionat.serverselector.interfaces.IGuiRepository;
import me.darrionat.serverselector.interfaces.IItemService;
import me.darrionat.serverselector.repositories.ConfigRepository;
import me.darrionat.serverselector.repositories.GuiRepository;
import me.darrionat.serverselector.services.BungeeService;
import me.darrionat.serverselector.services.ItemService;

public class Bootstrapper {
    private static Bootstrapper instance;
    /*
    Repositories
     */
    private IConfigRepository configRepo;
    private IGuiRepository guiRepo;
    /*
    Services
     */
    private IBungeeService bungeeService;
    private IItemService itemService;

    private Bootstrapper() {
    }

    public void init(ServerSelectorPlugin plugin) {
        configRepo = new ConfigRepository(plugin);
        guiRepo = new GuiRepository(plugin);
        bungeeService = new BungeeService(plugin);

        itemService = new ItemService(configRepo);
    }

    public IConfigRepository getConfigRepo() {
        return configRepo;
    }

    public IGuiRepository getGuiRepo() {
        return guiRepo;
    }

    public IBungeeService getBungeeService() {
        return bungeeService;
    }

    public IItemService getItemService() {
        return itemService;
    }

    public static Bootstrapper getBootstrapper() {
        if (instance == null)
            instance = new Bootstrapper();
        return instance;
    }
}