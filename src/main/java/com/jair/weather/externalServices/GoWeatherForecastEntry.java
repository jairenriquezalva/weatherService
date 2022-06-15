package com.jair.weather.externalServices;

import lombok.Data;

@Data
public class GoWeatherForecastEntry {
    String day;
    String temperature;
    String wind;
}
