package com.jair.weather.infraestructure.rest.mappers;

import com.jair.weather.domain.Weather;
import com.jair.weather.infraestructure.rest.schemas.WeatherResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
    WeatherResponse entityToRestResponse(Weather weather);
}
