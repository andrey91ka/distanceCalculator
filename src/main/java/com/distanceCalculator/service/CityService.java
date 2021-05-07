package com.distanceCalculator.service;

import com.distanceCalculator.entity.CityJaxb;
import com.distanceCalculator.model.City;
import com.distanceCalculator.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City saveCity(City city){
        return cityRepository.save(city);
    }
    public List<City> findAll(){
        return cityRepository.findAll();
    }





}
