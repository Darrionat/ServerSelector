package me.darrionat.serverselector;

import me.darrionat.pluginlib.ErrorHandler;
import me.darrionat.pluginlib.Plugin;
import me.darrionat.serverselector.commands.ServerSelectorCommand;
import me.darrionat.serverselector.gui.SelectorUi;
import me.darrionat.serverselector.interfaces.*;
import me.darrionat.serverselector.listeners.PlayerInteract;
import me.darrionat.serverselector.listeners.PlayerJoin;
import me.darrionat.serverselector.statics.Bootstrapper;
import me.darrionat.serverselector.utils.Errors;

public class ServerSelectorPlugin extends Plugin {
    // Error Handler
    private Errors errors;
    /*
    Repositories
    */
    private IConfigRepository configRepo;
    private IGuiRepository guiRepo;
    // private IMessageRepository messageRepo;
    /*
    Services
     */
    private IBungeeService bungeeService;
    private IItemService itemService;
    private IMessageService messageService;
    // UI
    private SelectorUi selectorUi;

    public void initPlugin() {
        initFields();
        log("Registering Bungee channel");
        bungeeService.setupChannel();
        registerCommands();
        registerListeners();
    }

    private void initFields() {
        Bootstrapper bootstrapper = Bootstrapper.getBootstrapper();
        bootstrapper.init(this);

        log("Initializing repositories");
        this.configRepo = bootstrapper.getConfigRepo();
        this.guiRepo = bootstrapper.getGuiRepo();

        log("Initializing services");
        this.bungeeService = bootstrapper.getBungeeService();
        this.itemService = bootstrapper.getItemService();
        this.messageService = bootstrapper.getMessageService();

        log("Registering GUI");
        selectorUi = new SelectorUi(this, guiRepo, bungeeService);
        errors = new Errors(messageService);
    }

    private void registerCommands() {
        new ServerSelectorCommand(this, messageService);
    }

    private void registerListeners() {
        log("Registering listeners");
        new PlayerJoin(this, itemService, configRepo);
        new PlayerInteract(this, itemService);
    }

    /**
     * Reloads all repositories asynchronously.
     */
    public void reinitializeRepositories() {
        getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {
            public void run() {
                for (Repository repo : Bootstrapper.getBootstrapper().getRepositories())
                    repo.init();
            }
        });
    }

    public void onDisable() {
        log("Unregistering Bungee channel");
        bungeeService.disableChannel();
    }

    public ErrorHandler getErrorHandler() {
        return errors;
    }

    public SelectorUi getSelectorUi() {
        return selectorUi;
    }
}