package com.jair.weather.domain.ports;

public interface TemperatureRepository {
    void saveTemperature(String cityId, Integer temperature);
    Integer getMaxTemp(String cityId);
    Integer getMinTemp(String cityId);
}
