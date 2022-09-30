package exercise.service;

import exercise.HttpClient;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, String> getWeatherInCity(City city) {
        Map<String, String> result = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        String response = client.get("http://weather/api/v2/cities/" + city.getName());
        try {
            result = mapper.readValue(response, Map.class);
        } catch (Exception e) {
            return result;
        }
        return result;
    }
    // END
}
