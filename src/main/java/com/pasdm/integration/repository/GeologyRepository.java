package com.pasdm.integration.repository;

import com.pasdm.integration.model.Geology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeologyRepository extends JpaRepository<Geology, Long> {
}