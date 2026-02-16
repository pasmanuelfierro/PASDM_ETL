package com.pasdm.integration.repository;

import com.pasdm.integration.model.MTTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MTTORepository extends JpaRepository<MTTO,Long> {
}
