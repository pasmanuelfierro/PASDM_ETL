package com.pasdm.integration.repository;

import com.pasdm.integration.model.LaboratoryQC;
import com.pasdm.integration.model.PlannedLabors;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlannedLaborsRepository extends JpaRepository<PlannedLabors, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.planned_labors ( fecha, zona, obra,  estructura, labor, row_hash,created_at  )
                           VALUES (
                               :#{#plannedLabors.fecha},
                               :#{#plannedLabors.zona},
                               :#{#plannedLabors.obra},
                               :#{#plannedLabors.estructura},
                               :#{#plannedLabors.labor},
                               :#{#plannedLabors.rowHash},
                                now() 
                           )
                           ON CONFLICT (row_hash)
                           DO UPDATE SET 
                               updated_at             = now();
            """, nativeQuery = true)
    void upsert(@Param("plannedLabors") PlannedLabors plannedLabors);

}
