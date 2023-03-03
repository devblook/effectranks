package me.bryang.effectranks.manager;

import me.bryang.effectranks.EffectRanks;
import me.bryang.effectranks.FileCreator;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import team.unnamed.inject.InjectAll;

@InjectAll
public class GroupManager {

    private EffectRanks plugin;

    private Permission permission;

    private FileCreator configFile;


    public void init() {
        if (Bukkit.getPluginManager().isPluginEnabled("Vault")){
            plugin.getLogger().info("Info - Disabling Vault support [You can use default group].");
            return;
        }

        RegisteredServiceProvider<Permission> provider = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);

        if (provider == null){
            return;
        }

        if (provider.getProvider().hasGroupSupport()){
            plugin.getLogger().severe("Err - You need a permisison plugin. Ejm. Vault");
            return;
        }

        permission = provider.getProvider();
    }

    public String returnGroup(Player player){

        if (permission == null){
            return "default";
        }


        if (configFile.getString("settings.toggle-type")
                .equalsIgnoreCase("group")){

            for (String group : permission.getPlayerGroups(player)){
                if (configFile.getConfigurationSection("groups." + group) != null){
                    return group;
                }
            }

        }else{

            for (String permission : configFile.getConfigurationSection("groups").getKeys(false)){
                if (!player.hasPermission(permission)){
                    continue;
                }

                return permission;
            }
        }

        return "default";

    }

}
