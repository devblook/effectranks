package me.bryang.effectranks.modules;

import me.bryang.effectranks.EffectRanks;
import me.bryang.effectranks.commands.EffectRankCommand;
import me.bryang.effectranks.listeners.PlayerSelectKeyListener;
import me.bryang.effectranks.manager.GroupManager;
import me.bryang.effectranks.services.ManagerService;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.key.TypeReference;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainModule extends AbstractModule {

    private final EffectRanks effectRanks;

    public MainModule(EffectRanks effectRanks){
        this.effectRanks = effectRanks;
    }

    @Override
    protected void configure() {
        bind(EffectRanks.class)
                .toInstance(effectRanks);

        bind(new TypeReference<Map<UUID, Integer>>(){})
                .toInstance(new HashMap<>());

        install(new FileModule());

        bind(ManagerService.class)
                .toInstance(new ManagerService());

        bind(GroupManager.class)
                .toInstance(new GroupManager());

        bind(EffectRankCommand.class)
                .toInstance(new EffectRankCommand());

        bind(PlayerSelectKeyListener.class)
                .toInstance(new PlayerSelectKeyListener());

        install(new ServicesModule());
    }
}
