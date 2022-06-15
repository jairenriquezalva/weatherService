package com.jair.weather.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherResponse {
    @JsonProperty("weather")
    String weatherDescription;
    @JsonProperty("temp")
    String temperature;
    String wind;
    String humidity;
    String ppm;
    @JsonProperty("max-temp")
    String maxTemperature;
    @JsonProperty("min-temp")
    String minTemperature;
}
