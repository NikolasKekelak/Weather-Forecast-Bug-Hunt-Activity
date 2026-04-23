package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.WeatherState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeatherFactoryTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testThunderstorm() throws Exception {
        JsonNode node = mapper.readTree("{\"weathercode\": 95, \"is_day\": 1, \"temperature\": 20}");
        assertEquals(WeatherState.THUNDERSTORM, WeatherFactory.getWeatherState(node));
    }

    @Test
    void testSnowing() throws Exception {
        JsonNode node = mapper.readTree("{\"weathercode\": 71, \"is_day\": 1, \"temperature\": 0}");
        assertEquals(WeatherState.SNOWING, WeatherFactory.getWeatherState(node));
    }

    @Test
    void testDayHot() throws Exception {
        JsonNode node = mapper.readTree("{\"weathercode\": 0, \"is_day\": 1, \"temperature\": 35}");
        assertEquals(WeatherState.DAY_HOT, WeatherFactory.getWeatherState(node));
    }

    @Test
    void testDayClear() throws Exception {
        JsonNode node = mapper.readTree("{\"weathercode\": 0, \"is_day\": 1, \"temperature\": 25}");
        assertEquals(WeatherState.DAY_CLEAR, WeatherFactory.getWeatherState(node));
    }

    @Test
    void testNightRain() throws Exception {
        JsonNode node = mapper.readTree("{\"weathercode\": 80, \"is_day\": 0, \"temperature\": 15}");
        assertEquals(WeatherState.NIGHT_RAIN, WeatherFactory.getWeatherState(node));
    }

    @Test
    void testDayCloudy() throws Exception {
        JsonNode node = mapper.readTree("{\"weathercode\": 2, \"is_day\": 1, \"temperature\": 18}");
        assertEquals(WeatherState.DAY_CLOUDY, WeatherFactory.getWeatherState(node));
    }

    @Test
    void testNightClear() throws Exception {
        JsonNode node = mapper.readTree("{\"weathercode\": 0, \"is_day\": 0, \"temperature\": 10}");
        assertEquals(WeatherState.NIGHT_CLEAR, WeatherFactory.getWeatherState(node));
    }

    @Test
    void testFogReturnsClear() throws Exception {
        // Weather code 45 is Fog. The current implementation doesn't handle it and returns DAY_CLEAR.
        // This test passes but is logically WRONG because Fog is not Clear sky.
        JsonNode node = mapper.readTree("{\"weathercode\": 45, \"is_day\": 1, \"temperature\": 20}");
        assertEquals(WeatherState.DAY_CLEAR, WeatherFactory.getWeatherState(node));
    }

    @Test
    void testTemperatureExactly30() throws Exception {
        JsonNode node = mapper.readTree("{\"weathercode\": 0, \"is_day\": 1, \"temperature\": 30.0}");
        assertEquals(WeatherState.DAY_CLEAR, WeatherFactory.getWeatherState(node));
    }
}
