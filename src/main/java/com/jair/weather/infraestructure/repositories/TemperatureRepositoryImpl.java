package com.jair.weather.infraestructure.repositories;

import com.jair.weather.domain.ports.TemperatureRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class TemperatureRepositoryImpl implements TemperatureRepository {

    Map<String, Integer> maxTemperatures;
    Map<String, Integer> minTemperatures;

    public TemperatureRepositoryImpl(){
        this.maxTemperatures = new HashMap<>();
        this.minTemperatures = new HashMap<>();
    }

    public void saveTemperature(String cityId, Integer temperature){
        if(Objects.isNull(maxTemperatures.get(cityId)) || maxTemperatures.get(cityId).compareTo(temperature)<0)
            maxTemperatures.put(cityId, temperature);
        if(Objects.isNull(minTemperatures.get(cityId)) || minTemperatures.get(cityId).compareTo(temperature)>0)
            minTemperatures.put(cityId, temperature);
    }

    public Integer getMaxTemp(String cityId){
        return maxTemperatures.get(cityId);
    }

    public Integer getMinTemp(String cityId){
        return minTemperatures.get(cityId);
    }

}
