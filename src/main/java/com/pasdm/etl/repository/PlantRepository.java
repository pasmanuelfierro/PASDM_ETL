package com.pasdm.etl.repository;
import com.pasdm.etl.model.Plant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Long> {
}