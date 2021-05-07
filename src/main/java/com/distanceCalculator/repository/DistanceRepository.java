package com.distanceCalculator.repository;

import com.distanceCalculator.model.Distance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistanceRepository extends JpaRepository<Distance, Long> {
}
