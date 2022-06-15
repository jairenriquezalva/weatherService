package com.jair.weather.services;

import com.jair.weather.beans.CitiesConfig;
import com.jair.weather.externalServices.GoWeatherResponse;
import com.jair.weather.externalServices.PurpleAirResponse;
import com.jair.weather.externalServices.PurpleAirResultEntry;
import com.jair.weather.rest.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final CitiesConfig citiesConfig;
    private final RestTemplate restTemplate;

    public WeatherResponse getWeather(String cityName){
        var weather = new WeatherResponse();
        var weatherServiceResponse = fetchGoWeatherResponse(cityName);
        var purpleAirServiceResponse = fetchPurpleAirResponse(cityName);
        weather.setWeatherDescription(weatherServiceResponse.getDescription());
        weather.setTemperature(weatherServiceResponse.getTemperature());
        weather.setWind(weatherServiceResponse.getWind());
        weather.setHumidity(purpleAirServiceResponse.getResults().stream().findFirst().orElseThrow().getHumidity());
        weather.setPpm(purpleAirServiceResponse.getResults().stream().findFirst().orElseThrow().getP03um());
        weather.setMaxTemperature(
                weatherServiceResponse.getForecast()
                        .stream().map(entry -> entry.getTemperature()).max(
                                (a, b) -> parseTemperature(a) - parseTemperature(b)
                        ).orElseThrow()
        );
        weather.setMinTemperature(
                weatherServiceResponse.getForecast()
                        .stream().map(entry -> entry.getTemperature()).max(
                                (a, b) -> parseTemperature(b) - parseTemperature(a)
                        ).orElseThrow()
        );
        return weather;
    };

    private GoWeatherResponse fetchGoWeatherResponse(String cityName){
        var cityId =  citiesConfig.getCityId(cityName);
        var params = new HashMap<String, String>();
        var url = UriComponentsBuilder
                .fromHttpUrl("https://goweather.herokuapp.com/weather/{city}")
                .buildAndExpand(cityName)
                .toString();
        var response = restTemplate.getForObject(url, GoWeatherResponse.class);
        return response;
    }

    private PurpleAirResponse fetchPurpleAirResponse(String cityName){
        var mockedResponse = new PurpleAirResponse();
        var mockedResultEntry = new PurpleAirResultEntry();
        mockedResultEntry.setHumidity("54");
        mockedResultEntry.setP03um("318.32");
        mockedResponse.setResults(List.of(mockedResultEntry));
        return mockedResponse;
    }

    private Integer parseTemperature(String temperature){
        Pattern temperatureValuePattern = Pattern.compile("(\\+|-)?\\d+");
        String temperatureString = null;
        var matcher = temperatureValuePattern.matcher(temperature);
        if(matcher.find())
            temperatureString = matcher.group();
        else
            throw new RuntimeException("Temperature parsing exception in the record temperature");
        return Integer.valueOf(temperatureString);
    }

}
