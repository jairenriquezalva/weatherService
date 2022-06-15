package com.jair.weather.externalServices;

import lombok.Data;

import java.util.List;

@Data
public class GoWeatherResponse {
    String temperature;
    String wind;
    String description;
    List<GoWeatherForecastEntry> forecast;
}
