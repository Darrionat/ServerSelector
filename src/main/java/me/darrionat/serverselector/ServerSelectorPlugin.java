package me.darrionat.serverselector;

import me.darrionat.pluginlib.ErrorHandler;
import me.darrionat.pluginlib.Plugin;
import me.darrionat.serverselector.gui.SelectorUi;
import me.darrionat.serverselector.interfaces.IBungeeService;
import me.darrionat.serverselector.interfaces.IConfigRepository;
import me.darrionat.serverselector.interfaces.IGuiRepository;
import me.darrionat.serverselector.interfaces.IItemService;
import me.darrionat.serverselector.listeners.PlayerJoin;
import me.darrionat.serverselector.statics.Bootstrapper;

public class ServerSelectorPlugin extends Plugin {
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

    public void initPlugin() {
        initFields();
        bungeeService.setupChannel();
        new PlayerJoin(this, itemService, configRepo);
        new SelectorUi(this, guiRepo, bungeeService);
    }

    private void initFields() {
        Bootstrapper bootstrapper = Bootstrapper.getBootstrapper();
        bootstrapper.init(this);
        this.configRepo = bootstrapper.getConfigRepo();
        this.guiRepo = bootstrapper.getGuiRepo();
        this.bungeeService = bootstrapper.getBungeeService();
        this.itemService = bootstrapper.getItemService();
    }

    public void onDisable() {
        bungeeService.disableChannel();
    }

    // TODO make an error handler, mainly for commands
    public ErrorHandler getErrorHandler() {
        return null;
    }
}