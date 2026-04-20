package org.example.model;

public class CityWeather {
    private final String       id;
    private String             cityName;
    private final double       latitude;
    private final double       longitude;
    private final double       temperature;
    private final String       description;
    private final WeatherState state;
    private final double       windSpeed;
    private final double       windDirection;
    private final String       time;
    private final boolean      isDay;

    public CityWeather(String id, String cityName, double latitude, double longitude, double temperature, String description, WeatherState state, 
                       double windSpeed, double windDirection, String time, boolean isDay) {
        this.id = id;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.description = description;
        this.state = state;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.time = time;
        this.isDay = isDay;
    }

    public String getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    public WeatherState getState() {
        return state;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public String getTime() {
        return time;
    }

    public boolean isDay() {
        return isDay;
    }
}