package com.jair.weather.domain;

import com.jair.weather.beans.CitiesConfig;
import com.jair.weather.domain.ports.GoWeatherService;
import com.jair.weather.domain.ports.PurpleAirService;
import com.jair.weather.domain.ports.dtos.GoWeatherResponse;
import com.jair.weather.domain.ports.dtos.PurpleAirResponse;
import com.jair.weather.domain.ports.dtos.PurpleAirResultEntry;
import com.jair.weather.domain.ports.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final CitiesConfig citiesConfig;
    private final TemperatureRepository temperatureRepository;
    private final GoWeatherService goWeatherService;
    private final PurpleAirService purpleAirService;

    public Weather getWeather(String cityName){
        var weather = new Weather();
        var cityId = citiesConfig.getCityId(cityName);
        var weatherServiceResponse = goWeatherService.fetch(cityName);
        var purpleAirServiceResponse = purpleAirService.fetch(cityId);
        applyPurpleAirResponse(purpleAirServiceResponse, weather);
        applyWeatherServiceResponse(weatherServiceResponse, weather, cityId);
        return weather;
    }

    private void applyWeatherServiceResponse(GoWeatherResponse weatherServiceResponse, Weather weather, String cityId) {
        var parsedTemperature = parseTemperature(weatherServiceResponse.getTemperature());
        var parsedWind = parseWind(weatherServiceResponse.getWind());
        weather.setWeatherDescription(weatherServiceResponse.getDescription());
        weather.setTemperature(parsedTemperature);
        temperatureRepository.saveTemperature(
                cityId,
                parsedTemperature
        );
        weather.setWind(parsedWind);
        weather.setMaxTemperature(temperatureRepository.getMaxTemp(cityId));
        weather.setMinTemperature(temperatureRepository.getMinTemp(cityId));
    }

    private void applyPurpleAirResponse(PurpleAirResponse purpleAirServiceResponse, Weather weather){
        var parsedHumidity = Integer.valueOf(
                purpleAirServiceResponse
                        .getResults()
                        .stream()
                        .findFirst()
                        .orElseThrow()
                        .getHumidity()
        );
        weather.setHumidity(parsedHumidity);
        weather.setPpm(purpleAirServiceResponse.getResults().stream().findFirst().orElseThrow().getP03um());
    }

    private Integer parseWind(String wind){
        Pattern windValuePattern = Pattern.compile("\\d+");
        String matcherString = null;
        var matcher = windValuePattern.matcher(wind);
        if(matcher.find())
            matcherString = matcher.group();
        else
            //Todo: improve exception management
            throw new RuntimeException("Wind parsing exception in the record temperature");
        return Integer.valueOf(matcherString);
    }


    private Integer parseTemperature(String temperature){
        Pattern temperatureValuePattern = Pattern.compile("(\\+|-)?\\d+");
        String matcherString = null;
        var matcher = temperatureValuePattern.matcher(temperature);
        if(matcher.find())
            matcherString = matcher.group();
        else
            //Todo: improve exception management
            throw new RuntimeException("Temperature parsing exception in the record temperature");
        return Integer.valueOf(matcherString);
    }

}
