package me.bryang.effectranks;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class PluginUtils {


    public static int convertTimeToInt(String timePath){

        String[] times = timePath.split(" ");

        int newTime = 0;

        int minutes = 60;
        int hour = minutes * 60;
        int day = hour * 24;
        int week = day * 7;
        int months = week * 4 + (day * 2);
        int year = months * 12;

        for (String timeFormatPath : times){

            int time = Integer.parseInt(timeFormatPath.substring(0, timeFormatPath.length() - 1));

            if (timeFormatPath.endsWith("y")){
                newTime = newTime + (time * year);
            }

            if (timeFormatPath.endsWith("m")){
                newTime = newTime + (time * months);
            }

            if (timeFormatPath.endsWith("w")){
                newTime = newTime + (time * week);
            }
            if (timeFormatPath.endsWith("d")){
                newTime = newTime + (time * day);
            }

            if (timeFormatPath.endsWith("h")){
                newTime = newTime + (time * hour);
            }

            if (timeFormatPath.endsWith("min")){
                newTime = newTime + (time * minutes);
            }
            if (timeFormatPath.endsWith("s")){
                newTime += time;
            }

        }

        return newTime;
    }

    public static String convertTimeToString(String timePath, ConfigurationSection section){

        String[] times = timePath.split(" ");
        StringBuilder stringBuilder = new StringBuilder();

        for (String timeFormatPath : times){

            int time = Integer.parseInt(timeFormatPath.substring(0, timeFormatPath.length() - 1));

            stringBuilder.append(" ");

            if (timeFormatPath.endsWith("y")){

                stringBuilder.append(section.getString((time == 1) ? "year" : "years"));
            }

            if (timeFormatPath.endsWith("m")) {

                stringBuilder.append(section.getString((time == 1) ? "month" : "months"));
            }

            if (timeFormatPath.endsWith("w")){

                stringBuilder.append(section.getString((time == 1) ? "week" : "weeks"));
            }
            if (timeFormatPath.endsWith("d")){

                stringBuilder.append(section.getString((time == 1) ? "day" : "days"));
            }

            if (timeFormatPath.endsWith("h")){

                stringBuilder.append(section.getString((time == 1) ? "hour" : "hours"));
            }

            if (timeFormatPath.endsWith("min")){

                stringBuilder.append(section.getString((time == 1) ? "minute" : "minutes"));
            }
            if (timeFormatPath.endsWith("s")){

                stringBuilder.append(section.getString((time == 1) ? "second" : "seconds"));
            }

        }

        return stringBuilder.substring(1);
    }

    public static PotionEffect stringToEffect(String path){

        String[] effectPath = path.split(",");

        PotionEffectType potionEffectType = PotionEffectType.getByName(effectPath[0]);

        if (potionEffectType == null){
            return null;
        }

        int duration = Integer.parseInt(effectPath[1]);
        int level = Integer.parseInt(effectPath[2]);

        return new PotionEffect(potionEffectType, duration, level);
    }


    public static void addPotionEffects(Player sender, List<String> effects){

        effects.forEach(effect -> {

            PotionEffect potionEffect = stringToEffect(effect);

            if (potionEffect == null){
                sender.sendMessage("Error: Please check config");
                return;
            }

            sender.addPotionEffect(potionEffect);

        });
    }

}
