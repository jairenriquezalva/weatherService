package com.jair.weather.infraestructure.rest.controllers;

import com.jair.weather.domain.WeatherServiceImpl;
import com.jair.weather.infraestructure.rest.schemas.WeatherResponse;
import com.jair.weather.infraestructure.rest.mappers.WeatherMapper;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WeatherController {

    private final WeatherServiceImpl service;
    private final WeatherMapper mapper;

    @GetMapping("/{cityName}")
    public ResponseEntity<WeatherResponse> get(@PathVariable("cityName") String cityName){
        var weather = service.getWeather(cityName);
        //Todo: improve exception management
        return ResponseEntity.of(Optional.of(mapper.entityToRestResponse(weather)));
    }

}
