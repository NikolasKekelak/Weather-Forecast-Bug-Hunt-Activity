package org.example.model;

public class CityWeather {
    private String id;
    private String cityName;
    private double latitude;
    private double longitude;
    private double temperature;
    private String description;
    private WeatherState state;
    private double windSpeed;
    private double windDirection;
    private String time;
    private boolean isDay;

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

    public void rename(CityWeather other) {
        this.id = other.id;
        this.cityName = other.cityName;
        this.latitude = other.latitude;
        this.longitude = other.longitude;
        this.temperature = other.temperature;
        this.description = other.description;
        this.state = other.state;
        this.windSpeed = other.windSpeed;
        this.windDirection = other.windDirection;
        this.time = other.time;
        this.isDay = other.isDay;
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