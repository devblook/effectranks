package me.bryang.effectranks;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

                String years = "years";

                if (time == 1){
                    years = years.substring(0, years.length() - 1);
                }

                stringBuilder.append(section.getString(years));
            }

            if (timeFormatPath.endsWith("m")){

                String month = "month";

                if (time == 1){
                    month = month.substring(0, month.length() - 1);
                }

                stringBuilder.append(section.getString(month));
            }

            if (timeFormatPath.endsWith("w")){

                String week = "week";

                if (time == 1){
                    week = week.substring(0, week.length() - 1);
                }

                stringBuilder.append(section.getString(week));
            }
            if (timeFormatPath.endsWith("d")){

                String days = "days";

                if (time == 1){
                    days = days.substring(0, days.length() - 1);
                }

                stringBuilder.append(section.getString(days));
            }

            if (timeFormatPath.endsWith("h")){

                String hours = "hours";

                if (time == 1){
                    hours = hours.substring(0, hours.length() - 1);
                }

                stringBuilder.append(section.getString(hours));
            }

            if (timeFormatPath.endsWith("min")){

                String min = "minutes";

                if (time == 1){
                    min = min.substring(0, min.length() - 1);
                }

                stringBuilder.append(section.getString(min));
            }
            if (timeFormatPath.endsWith("s")){

                String seconds = "seconds";

                if (time == 1){
                    seconds = seconds.substring(0, seconds.length() - 1);
                }

                stringBuilder.append(section.getString(seconds));
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



}
