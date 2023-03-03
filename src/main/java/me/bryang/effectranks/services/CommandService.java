package me.bryang.effectranks.services;

import me.bryang.effectranks.commands.EffectRankCommand;
import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;

import javax.inject.Inject;

public class CommandService implements Service{

    @Inject
    private EffectRankCommand effectRankCommand;

    @Override
    public void init() {

        CommandManager commandManager = new BukkitCommandManager("effect-ranks");

        PartInjector partInjector = PartInjector.create();

        partInjector.install(new BukkitModule());
        partInjector.install(new DefaultsModule());

        AnnotatedCommandTreeBuilder builder = new AnnotatedCommandTreeBuilderImpl(partInjector);
        commandManager.registerCommands(builder.fromClass(effectRankCommand));


    }
}
