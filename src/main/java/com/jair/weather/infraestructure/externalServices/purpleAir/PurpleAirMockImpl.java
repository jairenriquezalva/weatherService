package com.jair.weather.infraestructure.externalServices.purpleAir;

import com.jair.weather.beans.CitiesConfig;
import com.jair.weather.domain.ports.PurpleAirService;
import com.jair.weather.domain.ports.dtos.PurpleAirResponse;
import com.jair.weather.domain.ports.dtos.PurpleAirResultEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurpleAirMockImpl implements PurpleAirService {

    public PurpleAirResponse fetch(String cityName) {
        var mockedResponse = new PurpleAirResponse();
        var mockedResultEntry = new PurpleAirResultEntry();
        mockedResultEntry.setHumidity("54");
        mockedResultEntry.setP03um("318.32");
        mockedResponse.setResults(List.of(mockedResultEntry));
        return mockedResponse;
    }
}
