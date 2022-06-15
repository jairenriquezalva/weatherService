package com.jair.weather.domain.ports;

import com.jair.weather.domain.ports.dtos.GoWeatherResponse;

public interface GoWeatherService {
    GoWeatherResponse fetch(String cityName);
}
