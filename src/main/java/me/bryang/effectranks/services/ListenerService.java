package me.bryang.effectranks.services;

import me.bryang.effectranks.EffectRanks;
import me.bryang.effectranks.listeners.PlayerSelectKeyListener;
import me.bryang.effectranks.modules.ServicesModule;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import team.unnamed.inject.InjectAll;

import javax.inject.Inject;

@InjectAll
public class ListenerService implements Service {

    private PlayerSelectKeyListener playerSelectKeyListener;
    private EffectRanks plugin;


    @Override
    public void init() {

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(playerSelectKeyListener, plugin);
    }
}
