package me.bryang.effectranks;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileCreator extends YamlConfiguration {

    @Inject
    private EffectRanks plugin;

    private final String fileName;
    private final File file;

    public FileCreator(String fileName){

        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName + ".yml");
    }


    public void create(){

        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }

        try {
            load(file);

        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

    public void save(){
        try{
            save(file);
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void reload(){
        try{
            load(file);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public String getString(@NotNull String path){

        String newPath = super.getString(path);

        if (newPath == null){
            return "Err - Unknown path: " + path;
        }

        return ChatColor.translateAlternateColorCodes('&', newPath
                .replace("%n", "\n"));
    }

    @Override
    public List<String> getStringList(@NotNull String path){

        List<String> newPath = super.getStringList(path);

        if (newPath.isEmpty()){
            plugin.getLogger().info("Error: Unknown path." + path);
            return new ArrayList<>();
        }


        newPath.replaceAll(
                text -> {

            text = ChatColor.translateAlternateColorCodes('&', text);
            text = text.replace("&n", "\n");

            return text;
        });

        return newPath;
    }

}
