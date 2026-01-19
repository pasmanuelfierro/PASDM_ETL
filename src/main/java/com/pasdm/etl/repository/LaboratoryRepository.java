package com.pasdm.etl.repository;

import com.pasdm.etl.model.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {

}
