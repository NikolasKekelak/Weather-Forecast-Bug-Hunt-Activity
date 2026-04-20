package org.example.model;

public enum WeatherState {
    NIGHT_CLEAR("night_clear"),
    NIGHT_CLOUDY("night_partially_cloudy"),
    NIGHT_RAIN("raining"),
    DAY_CLEAR("day_clear"),
    DAY_CLOUDY("day_partially_cloudy"),
    DAY_RAIN("raining"),
    THUNDERSTORM("thunderstorm"),
    SNOWING("snowing"),
    DAY_HOT("day_hot");

    private final String key;

    WeatherState(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}