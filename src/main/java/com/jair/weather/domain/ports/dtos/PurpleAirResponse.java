package com.jair.weather.domain.ports.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PurpleAirResponse {

    List<PurpleAirResultEntry> results;
}
