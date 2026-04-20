package org.example.service;
    
import com.fasterxml.jackson.databind.JsonNode;
import org.example.model.WeatherState;

public class WeatherFactory {

    public static WeatherState getWeatherState(JsonNode current) {
        int code = current.get("weathercode").asInt();
        boolean isDay = current.get("is_day").asInt() == 1;


        // https://open-meteo.com/en/docs
        if (code >= 95 && code <= 99) {
            return WeatherState.THUNDERSTORM;
        } else if (code >= 71 && code <= 77 || code >= 85 && code <= 86) {
            return WeatherState.SNOWING;
        } else if (code >= 51 && code <= 67 || code >= 80 && code <= 82) {
            return isDay ? WeatherState.DAY_RAIN : WeatherState.NIGHT_RAIN;
        } else if (code >= 1 && code <= 3) {
            return isDay ? WeatherState.DAY_CLOUDY : WeatherState.NIGHT_CLOUDY;
        } else if (current.get("temperature").asDouble() > 30 && isDay) {
            return WeatherState.DAY_HOT;
        } else {
            return isDay ? WeatherState.DAY_CLEAR : WeatherState.NIGHT_CLEAR;
        }
    }

}