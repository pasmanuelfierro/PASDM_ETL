package com.pasdm.etl.repository;

import com.pasdm.etl.model.PlantV1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantV1Repository extends JpaRepository<PlantV1, Long> {
}