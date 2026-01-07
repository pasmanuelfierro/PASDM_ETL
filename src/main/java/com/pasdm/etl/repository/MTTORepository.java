package com.pasdm.etl.repository;

import com.pasdm.etl.model.MTTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MTTORepository extends JpaRepository<MTTO,Long> {
}
