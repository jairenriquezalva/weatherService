package com.jair.weather.infraestructure.rest.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherResponse {
    @JsonProperty("weather")
    String weatherDescription;
    @JsonProperty("temp")
    Integer temperature;
    Integer wind;
    Integer humidity;
    String ppm;
    @JsonProperty("max-temp")
    Integer maxTemperature;
    @JsonProperty("min-temp")
    Integer minTemperature;
}
