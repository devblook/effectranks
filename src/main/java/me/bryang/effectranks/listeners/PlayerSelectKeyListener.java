package me.bryang.effectranks.listeners;

import me.bryang.effectranks.FileCreator;
import me.bryang.effectranks.PluginUtils;
import me.bryang.effectranks.manager.GroupManager;
import me.bryang.effectranks.manager.SenderManager;
import me.bryang.effectranks.services.ManagerService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import team.unnamed.inject.InjectAll;

import javax.inject.Inject;
import java.util.List;


@InjectAll
public class PlayerSelectKeyListener implements Listener{

    private FileCreator configFile;

    private SenderManager senderManager;



    @EventHandler
    public void onShiftKey(PlayerToggleSprintEvent event) {

        if (!event.isSprinting()) {
            return;
        }

        if (!configFile.getString("settings.key")
                .equalsIgnoreCase("SHIFT")) {
            return;
        }

        senderManager.enablePlayerEffects(event.getPlayer());

    }


    @EventHandler
    public void onClickKey(PlayerInteractEvent event) {

        if (!configFile.getString("settings.key")
                .equalsIgnoreCase("LEFT-CLICK") &&

           !configFile.getString("settings.key")
                   .equalsIgnoreCase("RIGHT-CLICK")) {
            return;
        }

        senderManager.enablePlayerEffects(event.getPlayer());

    }
}
