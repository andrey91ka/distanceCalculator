package com.distanceCalculator.service;

import com.distanceCalculator.model.Distance;
import com.distanceCalculator.repository.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistanceService {
    private final DistanceRepository distanceRepository;


    public DistanceService(DistanceRepository distanceRepository) {
        this.distanceRepository = distanceRepository;
    }

    public Distance saveDistance(Distance distance){
        return distanceRepository.save(distance);
    }

    public List<Distance> findAll(){
        return distanceRepository.findAll();
    }
}
