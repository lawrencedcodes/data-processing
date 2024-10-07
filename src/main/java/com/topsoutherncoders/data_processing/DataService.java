package com.topsoutherncoders.data_processing;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class DataService {

    private static final String API_KEY = "";
    private static final String CITY = "London";

    public String getTemperature() {
        try {
            // Fetch weather data from OpenWeatherMap API
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&appid=" + API_KEY;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode mainNode = rootNode.path("main");

            // Handle missing temperature data
            if (mainNode.has("temp")) {
                double temperatureKelvin = mainNode.path("temp").asDouble();
                double temperatureCelsius = temperatureKelvin - 273.15;
                return String.format("%.2f°C", temperatureCelsius); // Format temperature
            } else {
                return "Temperature data is missing.";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // Handle exceptions properly in a real application
            return "Error fetching temperature.";
        }
    }
}
