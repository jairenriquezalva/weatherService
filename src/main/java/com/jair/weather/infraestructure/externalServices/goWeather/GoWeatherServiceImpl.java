package com.jair.weather.infraestructure.externalServices.goWeather;

import com.jair.weather.beans.CitiesConfig;
import com.jair.weather.domain.ports.GoWeatherService;
import com.jair.weather.domain.ports.dtos.GoWeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class GoWeatherServiceImpl implements GoWeatherService {

    private final CitiesConfig citiesConfig;
    private final RestTemplate restTemplate;

    public GoWeatherResponse fetch(String cityName){
        var url = UriComponentsBuilder
                .fromHttpUrl("https://goweather.herokuapp.com/weather/{city}")
                .buildAndExpand(cityName)
                .toString();
        var response = restTemplate.getForObject(url, GoWeatherResponse.class);
        return response;
    }
}
