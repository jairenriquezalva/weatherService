package com.jair.weather.domain;

import lombok.Data;

@Data
public class Weather {
    String weatherDescription;
    Integer temperature;
    Integer wind;
    Integer humidity;
    String ppm;
    Integer maxTemperature;
    Integer minTemperature;
}
