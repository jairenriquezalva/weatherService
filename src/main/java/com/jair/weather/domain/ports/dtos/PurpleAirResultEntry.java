package com.jair.weather.domain.ports.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PurpleAirResultEntry {
    String humidity;
    @JsonProperty("p_0_3_um")
    String p03um;
}
