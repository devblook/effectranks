package me.bryang.effectranks.modules;

import me.bryang.effectranks.EffectRanks;
import me.bryang.effectranks.manager.GroupManager;
import me.bryang.effectranks.services.CommandService;
import me.bryang.effectranks.services.ListenerService;
import me.bryang.effectranks.services.Service;
import me.fixeddev.commandflow.annotated.CommandClass;
import team.unnamed.inject.AbstractModule;

public class ServicesModule extends AbstractModule {

    @Override
    protected void configure() {

        multibind(Service.class)
                .asSet()
                .to(CommandService.class)
                .to(ListenerService.class)
                .to(ManagerService.class);


    }
}
