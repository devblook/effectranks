package me.bryang.effectranks.listeners;

import me.bryang.effectranks.FileCreator;
import me.bryang.effectranks.PluginUtils;
import me.bryang.effectranks.manager.GroupManager;
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

    private GroupManager groupManager;



    @EventHandler
    public void onShiftKey(PlayerToggleSprintEvent event) {

        if (!event.isSprinting()) {
            return;
        }

        if (!configFile.getString("settings.key")
                .equalsIgnoreCase("SHIFT")) {
            return;
        }

        Player sender = event.getPlayer();

        String playerRank = groupManager.returnGroup(sender);
        List<String> effects = configFile.getStringList(playerRank + ".effects");

        PluginUtils.addPotionEffects(sender, effects);

    }


    @EventHandler
    public void onClickKey(PlayerInteractEvent event) {

        if (!configFile.getString("settings.key")
                .equalsIgnoreCase("LEFT-CLICK") &&

           !configFile.getString("settings.key")
                   .equalsIgnoreCase("RIGHT-CLICK")) {
            return;
        }

        Player sender = event.getPlayer();

        String playerRank = groupManager.returnGroup(sender);
        List<String> effects = configFile.getStringList(playerRank + ".effects");

        PluginUtils.addPotionEffects(sender, effects);

    }
}
