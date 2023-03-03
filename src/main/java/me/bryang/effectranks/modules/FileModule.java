package me.bryang.effectranks.modules;

import me.bryang.effectranks.FileCreator;
import team.unnamed.inject.AbstractModule;

public class FileModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(FileCreator.class)
                .toInstance(new FileCreator("config"));

        bind(FileCreator.class)
                .named("messages")
                .toInstance(new FileCreator("messages"));

        bind(FileCreator.class)
                .named("players")
                .toInstance(new FileCreator("players"));

    }
}
