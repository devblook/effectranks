package me.bryang.effectranks;

import me.bryang.effectranks.modules.MainModule;
import me.bryang.effectranks.services.Service;
import org.bukkit.plugin.java.JavaPlugin;
import team.unnamed.inject.Injector;

import javax.inject.Inject;
import java.util.Set;

public class EffectRanks extends JavaPlugin {

    @Inject
    private Set<Service> services;


    @Override
    public void onEnable() {

        Injector injector = Injector
                .create(new MainModule(this));
        injector.injectMembers(this);

        services.forEach(Service::init);
    }

    @Override
    public void onDisable() {

    }
}
