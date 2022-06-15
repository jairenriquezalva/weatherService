package com.jair.weather.rest;

import com.jair.weather.services.WeatherService;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WeatherController {

    private final WeatherService service;

    @GetMapping("/{cityName}")
    public ResponseEntity<WeatherResponse> get(@PathVariable("cityName") String cityName){
        var weather = service.getWeather(cityName);
        return ResponseEntity.of(Optional.of(weather));
    }

}
