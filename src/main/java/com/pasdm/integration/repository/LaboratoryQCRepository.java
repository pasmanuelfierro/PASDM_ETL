package com.pasdm.integration.repository;

import com.pasdm.integration.model.Laboratory;
import com.pasdm.integration.model.LaboratoryQC;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LaboratoryQCRepository extends JpaRepository<LaboratoryQC, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.laboratory_qc (
                               fecha, lote, row_hash,
                               plata, minimo, optimo,
                               maximo, oro,
                               minimo2, optimo3,
                               maximo4, plomo, minimos,
                               optimo6,maximo7, zinc, minimo8,
                               optimo9,maximo10, created_at
                           )
                           VALUES (
                               :#{#laboratory.fecha},
                               :#{#laboratory.lote},
                               :#{#laboratory.rowHash},
            
                               :#{#laboratory.plata},
                               :#{#laboratory.minimo},
                               :#{#laboratory.optimo},
                               :#{#laboratory.maximo},
                               :#{#laboratory.oro},
                               :#{#laboratory.minimo2},
                               :#{#laboratory.optimo3},
                               :#{#laboratory.maximo4},
                               :#{#laboratory.plomo},
                               :#{#laboratory.minimos},
                               :#{#laboratory.optimo6},
                               :#{#laboratory.maximo7},
                               :#{#laboratory.zinc},
                               :#{#laboratory.minimo8},
                               :#{#laboratory.optimo9},
                               :#{#laboratory.maximo10},
                                now() 
                           )
                           ON CONFLICT (row_hash)
                           DO UPDATE SET
                               lote                   = EXCLUDED.lote,
                               plata                  = EXCLUDED.plata,
                               minimo                 = EXCLUDED.minimo,
                               optimo                 = EXCLUDED.optimo,
                               maximo                 = EXCLUDED.maximo,
                               oro                    = EXCLUDED.oro,
                               minimo2                = EXCLUDED.minimo2,
                               optimo3                = EXCLUDED.optimo3,
                               maximo4                = EXCLUDED.maximo4,
                               plomo                  = EXCLUDED.plomo,
                               minimos                = EXCLUDED.minimos,
                               optimo6                = EXCLUDED.optimo6,
                               maximo7                = EXCLUDED.maximo7,
                               zinc                   = EXCLUDED.zinc,
                               minimo8                = EXCLUDED.minimo8,
                               optimo9                = EXCLUDED.optimo9,
                               maximo10               = EXCLUDED.maximo10,
                               updated_at             = now();
            """, nativeQuery = true)
    void upsert(@Param("laboratory") LaboratoryQC laboratory);

}
