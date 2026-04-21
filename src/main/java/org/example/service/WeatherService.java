package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.model.CityWeather;
import org.example.model.WeatherState;

import java.io.IOException;

public class WeatherService {
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public CityWeather fetchWeather(String cityName) throws IOException {
        JsonNode geo = geocode(cityName);
        return fetchWeatherByCoords(
                String.valueOf(geo.get("id").asLong()),
                geo.get("name").asText(),
                geo.get("latitude").asDouble(),
                geo.get("longitude").asDouble()
                // test checkout
        );
    }

    public CityWeather fetchWeatherByCoords(String id, String name, double lat, double lon) throws IOException {
        String url = String.format("https://api.open-meteo.com/v1/forecast?latitude=%.2f&longitude=%.2f&current_weather=true", lat, lon);
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Weather API error: " + response);

            JsonNode current = mapper.readTree(response.body().string()).get("current_weather");
            return new CityWeather(
                    id, name, lat, lon,
                    current.get("temperature").asDouble(),
                    "Weather code: " + current.get("weathercode").asInt(),
                    WeatherFactory.getWeatherState(current),
                    current.get("windspeed").asDouble(),
                    current.get("winddirection").asDouble(),
                    current.get("time").asText(),
                    current.get("is_day").asInt() == 1
            );
        }
    }



    private JsonNode geocode(String cityName) throws IOException {
        String url = String.format("https://geocoding-api.open-meteo.com/v1/search?name=%s&count=1&language=en&format=json", cityName);
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Geocoding API error: " + response);

            JsonNode json = mapper.readTree(response.body().string());
            if (!json.has("results") || json.get("results").isEmpty()) {
                throw new IOException("City not found: " + cityName);
            }
            return json.get("results").get(0);
        }
    }
}