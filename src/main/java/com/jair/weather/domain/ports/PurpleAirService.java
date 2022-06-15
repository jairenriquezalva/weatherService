package com.jair.weather.domain.ports;

import com.jair.weather.domain.ports.dtos.PurpleAirResponse;

public interface PurpleAirService {
    PurpleAirResponse fetch(String cityName);
}
