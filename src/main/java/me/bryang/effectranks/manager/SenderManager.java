package me.bryang.effectranks.manager;

import me.bryang.effectranks.FileCreator;
import me.bryang.effectranks.PluginUtils;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import team.unnamed.inject.InjectAll;

import javax.inject.Named;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@InjectAll
public class SenderManager {


    private FileCreator configFile;

    @Named("messages")
    private FileCreator messagesFile;

    @Named("players")
    private FileCreator playersFile;

    private GroupManager groupManager;
    private Map<UUID, Integer> playerCooldown;


    public void enablePlayerEffects(Player sender){

        String playerRank = groupManager.returnGroup(sender);
        List<String> effects = configFile.getStringList(playerRank + ".effects");

        effects.forEach(effect -> {

            String[] effectPath = effect.split(",");

            PotionEffectType potionEffectType = PotionEffectType.getByName(effectPath[0]);

            if (potionEffectType == null){
                sender.sendMessage("Error: Check config");
                return;
            }

            int duration = Integer.parseInt(effectPath[1]);
            int level = Integer.parseInt(effectPath[2]);

            PotionEffect potionEffect = new PotionEffect(potionEffectType, duration, level);
            sender.addPotionEffect(potionEffect);

        });

        long currentTime = System.currentTimeMillis() / 1000;
        String senderCooldown = configFile.getString(playerRank + ".cooldown");

        if (senderCooldown == null){
            sender.sendMessage("Error: Please check config.");
            return;
        }

        int time = PluginUtils.convertTimeToInt(senderCooldown);

        playerCooldown.put(sender.getUniqueId(), Math.round(currentTime - time));

        playersFile.set(sender.getUniqueId().toString(), currentTime - time);
        playersFile.save();
        sender.sendMessage(messagesFile.getString("plugin.enabled"));
    }

}
