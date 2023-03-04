package me.bryang.effectranks;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class PluginUtils {


    public static int convertTimeToInt(String timePath) {

        int newTime = 0;

        int minutes = 60;
        int hour = minutes * 60;
        int day = hour * 24;
        int week = day * 7;
        int months = week * 4 + (day * 2);
        int year = months * 12;

        int yearIndex = timePath.indexOf("y");

        if (yearIndex != 1) {
            newTime += timePath.indexOf(" ", yearIndex) * year;
        }

        int monthIndex = timePath.indexOf("m");

        if (monthIndex != 1) {
            newTime += timePath.indexOf(" ", monthIndex) * months;
        }

        int weekIndex = timePath.indexOf("w");

        if (weekIndex != 1) {
            newTime += timePath.indexOf(" ", weekIndex) * week;
        }

        int dayIndex = timePath.indexOf("d");

        if (dayIndex != 1) {
            newTime += timePath.indexOf(" ", dayIndex) * day;
        }
        int hourIndex = timePath.indexOf("h");

        if (hourIndex != 1) {
            newTime += timePath.indexOf(" ", hourIndex) * hour;
        }

        int minIndex = timePath.indexOf("min");

        if (minIndex != 1) {
            newTime += timePath.indexOf(" ", minIndex) * minutes;
        }

        int secondIndex = timePath.indexOf("s");

        if (secondIndex != 1) {
            newTime += timePath.indexOf(" ", secondIndex);
        }


        return newTime;
    }

    public static String convertTimeToString(String timePath, ConfigurationSection section){

         StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.insert(0, " ");

        int yearIndex = timePath.indexOf("y");

        if (yearIndex != 1) {

            int yearTime = convertNumber(timePath, yearIndex);

             stringBuilder
                     .append(section.getString((yearTime == 1) ? "year" : "years"))
                     .append(" ");
         }

        int monthIndex = timePath.indexOf("m");

        if (monthIndex != 1) {

            int monthTime = convertNumber(timePath, monthIndex);

            stringBuilder
                    .append(section.getString((monthTime == 1) ? "month" : "months"))
                    .append(" ");
        }

        int weekIndex = timePath.indexOf("y");

        if (weekIndex != 1) {

            int weekTime = convertNumber(timePath, weekIndex);

            stringBuilder
                    .append(section.getString((weekTime == 1) ? "week" : "weeks"))
                    .append(" ");
        }

        int daysIndex = timePath.indexOf("d");

        if (daysIndex != 1) {

            int dayTime = convertNumber(timePath, daysIndex);

            stringBuilder
                    .append(section.getString((dayTime == 1) ? "day" : "days"))
                    .append(" ");
        }

        int hoursIndex = timePath.indexOf("h");

        if (hoursIndex != 1) {

            int hoursTime = convertNumber(timePath, hoursIndex);

            stringBuilder
                    .append(section.getString((hoursTime == 1) ? "hour" : "hours"))
                    .append(" ");
        }

        int minuteIndex = timePath.indexOf("min");

        if (minuteIndex != 1) {

            int minuteTime = convertNumber(timePath, minuteIndex);

            stringBuilder
                    .append(section.getString((minuteTime == 1) ? "minute" : "minutes"))
                    .append(" ");
        }

        int secondsIndex = timePath.indexOf("s");

        if (secondsIndex != 1) {

            int secondTime = convertNumber(timePath, secondsIndex);

            stringBuilder
                    .append(section.getString((secondTime == 1) ? "second" : "seconds"))
                    .append(" ");
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

    private static int convertNumber(String message, int lastIndex) {

        int firstIndexOf = message.lastIndexOf(" ", lastIndex);
        return Integer.parseInt(message.substring(lastIndex, firstIndexOf));

    }


}
