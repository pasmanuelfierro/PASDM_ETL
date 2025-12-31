package com.pasdm.etl.repository;

import com.pasdm.etl.model.Geology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeologyRepository extends JpaRepository<Geology, Long> {
}