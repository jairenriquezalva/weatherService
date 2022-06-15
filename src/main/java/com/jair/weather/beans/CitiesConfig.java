package com.jair.weather.beans;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@PropertySource("classpath:cities.properties")
public class CitiesConfig {

    private final Environment env;

    public String getCityId(String cityName){
        return env.getProperty("weather."  + cityName);
    };

}
