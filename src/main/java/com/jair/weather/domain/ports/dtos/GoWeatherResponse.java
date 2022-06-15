package com.jair.weather.domain.ports.dtos;

import lombok.Data;

import java.util.List;

@Data
public class GoWeatherResponse {
    String temperature;
    String wind;
    String description;
}
