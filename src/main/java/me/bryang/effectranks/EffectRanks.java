package me.bryang.effectranks;

import me.bryang.effectranks.modules.MainModule;
import org.bukkit.plugin.java.JavaPlugin;
import team.unnamed.inject.Injector;

public class EffectRanks extends JavaPlugin {



    @Override
    public void onEnable() {

        Injector injector = Injector.create(new MainModule(this));
        injector.injectMembers(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
