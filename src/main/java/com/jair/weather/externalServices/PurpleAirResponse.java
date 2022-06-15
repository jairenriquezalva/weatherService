package com.jair.weather.externalServices;

import lombok.Data;

import java.util.List;

@Data
public class PurpleAirResponse {

    List<PurpleAirResultEntry> results;
}
