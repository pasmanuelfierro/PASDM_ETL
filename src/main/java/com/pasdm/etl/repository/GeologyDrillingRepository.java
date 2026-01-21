package com.pasdm.etl.repository;

import com.pasdm.etl.model.GeologyDrilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GeologyDrillingRepository extends JpaRepository<GeologyDrilling, Long> {
    @Modifying
    @Transactional
    @Query(value = """
    INSERT INTO etl.geology_drilling (
        fecha,
        bno,
        empresa,
        maquina,
        fondo_dia_anterior_m,
        fondo_actual_m,
        avance_dia_m,
        status,
        estacion,
        jv_holes,
        target,
        target2,
        row_hash
    )
    VALUES (
        :#{#gd.fecha},
        :#{#gd.bno},
        :#{#gd.empresa},
        :#{#gd.maquina},
        :#{#gd.fondoDiaAnteriorM},
        :#{#gd.fondoActualM},
        :#{#gd.avanceDiaM},
        :#{#gd.status},
        :#{#gd.estacion},
        :#{#gd.jvHoles},
        :#{#gd.target},
        :#{#gd.target2},
        :#{#gd.rowHash}

    )
    ON CONFLICT (row_hash)
    DO UPDATE SET
        empresa = EXCLUDED.empresa,
        maquina = EXCLUDED.maquina,
        fondo_dia_anterior_m = EXCLUDED.fondo_dia_anterior_m,
        fondo_actual_m = EXCLUDED.fondo_actual_m,
        avance_dia_m = EXCLUDED.avance_dia_m,
        status = EXCLUDED.status,
        estacion = EXCLUDED.estacion,
        jv_holes = EXCLUDED.jv_holes,
        target = EXCLUDED.target,
        target2 = EXCLUDED.target2,
        updated_at = now()
""", nativeQuery = true)
    void upsert(@Param("gd") GeologyDrilling gd);
}
