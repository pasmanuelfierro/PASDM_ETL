package com.pasdm.etl.repository;

import com.pasdm.etl.model.Development;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DevelopmentRepository extends JpaRepository<Development, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.development (
                row_hash,
                fecha,
                grupo,
                prioridad,
                lote,
                status,
                mina,
                zona,
                costos,
                elaborado_por,
                min_tep,
                obra,
                estructura,
                lugar,
                metros,
                created_at
            )
            VALUES (
                :#{#d.rowHash},
                :#{#d.fecha},
                :#{#d.grupo},
                :#{#d.prioridad},
                :#{#d.lote},
                :#{#d.status},
                :#{#d.mina},
                :#{#d.zona},
                :#{#d.costos},
                :#{#d.elaboradoPor},
                :#{#d.minTep},
                :#{#d.obra},
                :#{#d.estructura},
                :#{#d.lugar},
                :#{#d.metros},
                now()
            )
            ON CONFLICT (row_hash)
            DO UPDATE SET
               grupo          = EXCLUDED.grupo,
               prioridad      = EXCLUDED.prioridad,
               lote           = EXCLUDED.lote,
               status         = EXCLUDED.status,
               mina           = EXCLUDED.mina,
               zona           = EXCLUDED.zona,
               costos         = EXCLUDED.costos,
               elaborado_por  = EXCLUDED.elaborado_por,
               min_tep        = EXCLUDED.min_tep,
               obra           = EXCLUDED.obra,
               estructura     = EXCLUDED.estructura,
               lugar          = EXCLUDED.lugar,
               metros         = EXCLUDED.metros,
               updated_at     = now();
            """, nativeQuery = true)
    void upsert(@Param("d") Development d);

}