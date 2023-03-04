package me.bryang.effectranks.commands;

import me.bryang.effectranks.FileCreator;
import me.bryang.effectranks.PluginUtils;
import me.bryang.effectranks.manager.GroupManager;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import team.unnamed.inject.InjectAll;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@InjectAll
@Command(
        names = {"effectranks", "effectr", "er"},
        desc = "Main command.")

public class EffectRankCommand implements CommandClass {

    private FileCreator configFile;

    @Named("messages")
    private FileCreator messagesFile;

    @Named("players")
    private FileCreator playersFile;
    
    private GroupManager groupManager;

    private Map<UUID, Integer> playerCooldown;


    @Command(
            names = {"", "help"},
            desc = "Help subcommand")

    public void onMainSubCommand(@Sender Player sender){

        messagesFile.getStringList("plugin.help")
                .forEach(sender::sendMessage);
    
    }

    @Command(
            names = "enable")
    public void onOnSubCommand(@Sender Player sender){

        String playerRank = groupManager.returnGroup(sender);
        List<String> effects = configFile.getStringList(playerRank + ".effects");

        PluginUtils.addPotionEffects(sender, effects);

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
    }

    @Command(
            names = "convert")
    public void onConvertSubCommand(@Sender Player sender){

        String playerRank = groupManager.returnGroup(sender);
        
        List<String> effects = configFile.getStringList(playerRank + ".effects");
        
        String name = configFile.getString( "settings.potion.name")
                    .replace("%rank_name%", sender.getName().substring(7));
        
        List<String> lore = new ArrayList<>();

        for (String newText : configFile.getStringList("potion.lore")){
            
            if (newText.startsWith("%vl%")){
                for (String effect : effects){
                    
                    String[] effectPath = effect.split(",");

                    lore.add(newText
                            .replace("%name%", effectPath[0].toUpperCase())
                            .replace("%duration%", PluginUtils.convertTimeToString(effectPath[1], configFile.getConfigurationSection("settings.time")))
                            .replace("%level%", effectPath[2]));

                }
            }

            lore.add(newText);
        }

        ItemStack itemStack = new ItemStack(Material.POTION, 1);

        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();

        effects.forEach(effect -> {

            PotionEffect potionEffect = PluginUtils.stringToEffect(effect);

            if (potionEffect == null){
                sender.sendMessage("Error: Please check config");
                return;
            }
            potionMeta.addCustomEffect(potionEffect, false);

        });

        itemStack.setItemMeta(potionMeta);
    }

    @Command(
            names = "reload",
            permission = "effectranks.reload")
    public void onReloadSubCommand(@Sender Player sender){

        configFile.reload();
        messagesFile.reload();
        sender.sendMessage(messagesFile.getString("admin.reload"));
    }

}
