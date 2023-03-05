package me.bryang.effectranks;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class PluginUtils {

// Time format: 1y 2m 3w 4d 5h 6min 7s

    public static int convertTimeToInt(String timePath) {

        int newTime = 0;

        int minutes = 60;
        int hour = minutes * 60;
        int day = hour * 24;
        int week = day * 7;
        int months = week * 4 + (day * 2);
        int year = months * 12;

        String[] formatChars = {"y", "m", "w", "d", "h", "min", "s"};
        int[] timeChars = {year, months, week, day, minutes, 1};


        for (int id = 0; id < 5; id++){

            int formatIndex = timePath.indexOf(formatChars[id]);
            newTime += convertNumber(" ", formatIndex) * timeChars[id];
        }

        return newTime;
    }

    public static String convertTimeToString(String timePath, ConfigurationSection section){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.insert(0, " ");

        String[] formatChars = {"y", "m", "w", "d", "h", "min", "s"};
        String[] pathChars = {"years", "months", "weeks", "days", "hours", "minutes", "seconds"};

        for (int id = 0; id < 5; id++) {

            int numberIndex = timePath.indexOf(formatChars[id]);

            if (numberIndex != -1){

                String pathType = pathChars[id];
                int formatTime = convertNumber(timePath, numberIndex);

                if (formatTime != -1) pathType = pathType.substring(0, pathType.length() - 1);

                stringBuilder
                        .append(section.getString(pathType))
                        .append(" ");
            }

        }

        return stringBuilder.toString();
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

    private static int convertNumber(String message, int lastIndex) {

        int firstIndexOf = message.lastIndexOf(" ", lastIndex);
        return Integer.parseInt(message.substring(lastIndex, firstIndexOf));

    }


}
