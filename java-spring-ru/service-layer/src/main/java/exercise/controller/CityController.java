package exercise.controller;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping("/cities/{id}")
    public Map<String, String> getCityWeather(@PathVariable Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found"));
        return weatherService.getWeatherInCity(city);
    }

    @GetMapping("search")
    public List<Map<String, String>> searchWeather(@RequestParam(defaultValue = "") String name) {
        List<City> cities = cityRepository.findByNameStartsWithIgnoreCaseOrderByName(name);
        List<Map<String, String>> result = cities.stream()
                .map(item -> weatherService.getWeatherInCity(item))
                .map(item -> {
                    item.remove("cloudy");
                    item.remove("wind");
                    item.remove("humidity");
                    return item;
                })
                .collect(Collectors.toList());
        return result;
    }
    // END
}

