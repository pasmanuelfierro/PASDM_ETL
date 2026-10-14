package com.pasdm.integration.repository;

import com.pasdm.integration.model.SourceConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceConfigurationRepository extends JpaRepository<SourceConfiguration, Long> {

}
