package com.pasdm.integration.repository;

import com.pasdm.integration.model.DieselReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DieselReportRepository extends JpaRepository<DieselReport, Long> {
    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO etl.diesel_report (
                                             estacion,
                                             flota,
                                             area,
                                             modelo,
                                             dispositivo,
                                             cantidad,
                                             producto,
                                             fecha,
                                             interior_superficie,
                                             familia,
                                             colorada_contratista,
                                             mes,
                                             row_hash
                                         ) VALUES (
                                             :#{#dieselReport.estacion},
                                             :#{#dieselReport.flota},
                                             :#{#dieselReport.area},
                                             :#{#dieselReport.modelo},
                                             :#{#dieselReport.dispositivo},
                                             :#{#dieselReport.cantidad},
                                             :#{#dieselReport.producto},
                                             :#{#dieselReport.fecha},
                                             :#{#dieselReport.interiorSuperficie},
                                             :#{#dieselReport.familia},
                                             :#{#dieselReport.coloradaContratista},
                                             :#{#dieselReport.mes},
                                             :#{#dieselReport.rowHash}
                                         )
                                         ON CONFLICT (row_hash)
                                         DO UPDATE SET
                                             flota = EXCLUDED.flota,
                                             area = EXCLUDED.area,
                                             modelo = EXCLUDED.modelo,
                                             dispositivo = EXCLUDED.dispositivo,
                                             cantidad = EXCLUDED.cantidad,
                                             producto = EXCLUDED.producto,
                                             fecha = EXCLUDED.fecha,
                                             interior_superficie = EXCLUDED.interior_superficie,
                                             familia = EXCLUDED.familia,
                                             colorada_contratista = EXCLUDED.colorada_contratista,
                                             mes = EXCLUDED.mes,
                                             updated_at = now();
            """, nativeQuery = true)
    void upsert(@Param("dieselReport") DieselReport dieselReport);
}